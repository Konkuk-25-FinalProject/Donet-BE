package com.donet.donet.user.application;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.global.exception.CustomException;
import com.donet.donet.user.application.port.in.GetMyPageUsecase;
import com.donet.donet.user.application.port.in.dto.GetMyPageResponse;
import com.donet.donet.user.application.port.out.FindUserPort;
import com.donet.donet.user.domain.JoinedDonation;
import com.donet.donet.user.domain.RegisteredDonation;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class GetMyPageService implements GetMyPageUsecase {
    private final FindUserPort findUserPort;
    private final FindDonationPort findDonationPort;

    @Override
    public GetMyPageResponse getPage(Long userId) {
        User user = findUserPort.findById(userId).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<JoinedDonation> joined = findDonationPort.findJoinedDonations(user, 20);
        List<RegisteredDonation> registered = findDonationPort.findRegisteredDonations(user, 20);
        return new GetMyPageResponse(user.getNickname(),
                user.getWalletAddress(),
                user.getProfileImage(),
                joined,
                registered);
    }
}
