package com.donet.donet.user.application.port.in;

import com.donet.donet.user.application.port.in.dto.GetMyPageResponse;

public interface GetMyPageUsecase {
    GetMyPageResponse getPage(Long userId);
}
