package com.donet.donet.user.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.user.adapter.in.web.dto.GetMyPageApiResponse;
import com.donet.donet.user.application.port.in.GetMyPageUsecase;
import com.donet.donet.user.application.port.in.dto.GetMyPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.GET_MY_PAGE;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GetMyPageController implements UserController{
    private final GetMyPageUsecase getMyPageUsecase;

    @Operation(
            summary = "마이페이지 조회 API",
            description = """
                    유저의 마이페이지를 조회할 수 있다. 토큰 인증이 필요하다
                    """
    )
    @CustomExceptionDescription(GET_MY_PAGE)
    @GetMapping("users/me")
    public BaseResponse<GetMyPageApiResponse> getMyPage(@Parameter(hidden = true) @CurrentUserId Long userId){
        log.info("[getMyPage] userId = {}", userId);
        GetMyPageResponse resp = getMyPageUsecase.getPage(userId);
        return new BaseResponse<>(GetMyPageApiResponse.from(resp));
    }
}
