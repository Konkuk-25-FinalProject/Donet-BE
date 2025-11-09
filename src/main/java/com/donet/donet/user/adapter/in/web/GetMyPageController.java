package com.donet.donet.user.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.user.adapter.in.web.dto.GetMyPageApiResponse;
import com.donet.donet.user.application.port.in.GetMyPageUsecase;
import com.donet.donet.user.application.port.in.dto.GetMyPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GetMyPageController {
    private final GetMyPageUsecase getMyPageUsecase;

    @GetMapping("users/me")
    public BaseResponse<GetMyPageApiResponse> getMyPage(@CurrentUserId Long userId){
        log.info("[getMyPage] userId = {}", userId);
        GetMyPageResponse resp = getMyPageUsecase.getPage(userId);
        return new BaseResponse<>(GetMyPageApiResponse.from(resp));
    }
}
