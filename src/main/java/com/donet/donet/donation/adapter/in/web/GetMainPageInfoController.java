package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.GetMainPageInfoUsecase;
import com.donet.donet.donation.application.port.in.dto.response.GetMainPageInfoResponse;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@Tag(
        name = "Main Page",
        description = "메인페이지 관련 API들"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/mainPage")
public class GetMainPageInfoController{
    private final GetMainPageInfoUsecase getMainPageInfoUsecase;

    @Operation(
            summary = "메인페이지 정보 조회 API",
            description = """
                    메인페이지에서 보여질 정보를 조회하는 API입니다. 
                    Top3 기부, 추천 기부, 리뷰 정보를 조회할 수 있습니다.
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @GetMapping()
    public BaseResponse<GetMainPageInfoResponse> getMainPageInfo(@Parameter(hidden = true) @CurrentUserId Long userId) {
        return new BaseResponse<>(getMainPageInfoUsecase.getMainPageInfo(userId));
    }
}
