package com.donet.donet.donation.application.port.in;

import com.donet.donet.donation.application.port.in.dto.response.GetMainPageInfoResponse;

public interface GetMainPageInfoUsecase {
    GetMainPageInfoResponse getMainPageInfo(Long userId);
}
