package com.donet.donet.auth.adapter.in.web;

import com.donet.donet.auth.adapter.in.web.dto.ReissueTokenRequest;
import com.donet.donet.auth.application.port.in.ReissueTokenUsecase;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenCommand;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenResponse;
import com.donet.donet.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReissueTokenController {
    private final ReissueTokenUsecase reissueTokenUsecase;
    @PostMapping("/auth/reissue/token")
    public BaseResponse<ReissueTokenResponse> reissueToken(@RequestBody ReissueTokenRequest request){
        ReissueTokenCommand command = new ReissueTokenCommand(request.refreshToken());
        return new BaseResponse(reissueTokenUsecase.reissue(command));
    }
}
