package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.CreateDonationUsecase;
import com.donet.donet.donation.application.port.in.command.CreateDonationCommand;
import com.donet.donet.donation.application.port.out.CreateDonationPort;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDonationService implements CreateDonationUsecase {
    private final CreateDonationPort createDonationPort;
    private final ImageUploaderPort imageUploaderPort;

    @Override
    public void createDonation(CreateDonationCommand command) {
        //Donation 객체 만들기

        //port 메소드 호출

    }
}
