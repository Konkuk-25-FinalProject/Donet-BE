package com.donet.donet.global.config;

import com.donet.donet.global.response.BaseErrorResponse;
import com.donet.donet.global.response.status.BaseExceptionResponseStatus;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.global.swagger.ExampleHolder;
import com.donet.donet.global.swagger.SwaggerResponseDescription;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.groupingBy;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .version("1.0")
                .title("Donet API 명세서")
                .description("Donet API 명세입니다.");

        String jwtSchemeName = "JWT Authentication";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            CustomExceptionDescription customExceptionDescription = handlerMethod.getMethodAnnotation(
                    CustomExceptionDescription.class);

            // CustomExceptionDescription 어노테이션 단 메소드 적용
            if (customExceptionDescription != null) {
                generateErrorCodeResponseExample(operation, customExceptionDescription.value());
            }

            return operation;
        };
    }


    private void generateErrorCodeResponseExample(
            Operation operation, SwaggerResponseDescription type) {

        ApiResponses responses = operation.getResponses();

        Set<BaseExceptionResponseStatus> baseExceptionResponseStatusSet = type.getExceptionResponseStatusSet();

        Map<Integer, List<ExampleHolder>> statusWithExampleHolders =
                baseExceptionResponseStatusSet.stream()
                        .map(
                                baseExceptionResponseStatus -> {
                                    return ExampleHolder.builder()
                                            .holder(
                                                    getSwaggerExample(baseExceptionResponseStatus))
                                            .code(baseExceptionResponseStatus.getStatus())
                                            .name(baseExceptionResponseStatus.toString())
                                            .build();
                                }
                        ).collect(groupingBy(ExampleHolder::getCode));
        addExamplesToResponses(responses, statusWithExampleHolders);
    }


    private Example getSwaggerExample(BaseExceptionResponseStatus status) {
        Example example = new Example();

        BaseErrorResponse errorResponse = new BaseErrorResponse(status);
        example.setValue(errorResponse);
        example.description(status.getMessage());

        return example;
    }


    private void addExamplesToResponses(
            ApiResponses responses, Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
        statusWithExampleHolders.forEach((status, holders) -> {
            String key = status.toString();

            // 1) 기존 ApiResponse가 있으면 재사용, 없으면 생성
            ApiResponse apiResponse = responses.computeIfAbsent(key, k -> new ApiResponse().description(""));

            // 2) content 초기화/재사용
            Content content = apiResponse.getContent();
            if (content == null) {
                content = new Content();
                apiResponse.setContent(content);
            }

            // 3) application/json MediaType 초기화/재사용
            MediaType mediaType = content.get("application/json");
            if (mediaType == null) {
                mediaType = new MediaType();
                content.addMediaType("application/json", mediaType);
            }

            // 4) 여러 개 example 누적 추가 (키 충돌 방지)
            for (int i = 0; i < holders.size(); i++) {
                ExampleHolder h = holders.get(i);
                String exampleName = uniqueExampleName(mediaType, h.getName(), i); // 아래 함수 참고
                mediaType.addExamples(exampleName, h.getHolder());
            }
        });
    }

    private String uniqueExampleName(MediaType mediaType, String base, int idxHint) {
        String name = (base == null || base.isBlank()) ? ("example-" + idxHint) : base;

        // 이름 중복이면 suffix를 붙여 충돌 방지
        int suffix = 1;
        while (mediaType.getExamples() != null && mediaType.getExamples().containsKey(name)) {
            name = base + "-" + suffix++;
        }
        return name;
    }

}
