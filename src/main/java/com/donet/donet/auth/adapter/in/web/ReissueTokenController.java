package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.adapter.in.web.dto.ReissueTokenRequest;
import com.donet.donet.auth.application.port.in.ReissueTokenUsecase;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenCommand;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenResponse;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.global.swagger.SwaggerResponseDescription;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReissueTokenController implements AuthController{
    private final ReissueTokenUsecase reissueTokenUsecase;

    @Operation(
            summary = "토큰 재발급",
            description = "리프레시 토큰을 보내면 새로운 엑세스토큰과 리프레시 토큰을 받을 수 있다"
    )
    @CustomExceptionDescription(SwaggerResponseDescription.REISSUE_TOKEN)
    @PostMapping("/auth/reissue/token")
    public BaseResponse<ReissueTokenResponse> reissueToken(@RequestBody ReissueTokenRequest request){
        ReissueTokenCommand command = new ReissueTokenCommand(request.refreshToken());
        return new BaseResponse(reissueTokenUsecase.reissue(command));
    }
}
