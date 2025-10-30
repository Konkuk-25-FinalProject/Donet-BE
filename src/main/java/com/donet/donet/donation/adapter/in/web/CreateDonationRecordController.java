package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.adapter.in.web.dto.CreateDonationRecordRequest;
import com.donet.donet.donation.application.port.in.AddDonationRecordUsecase;
import com.donet.donet.donation.application.port.in.command.AddDonationRecordCommand;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class CreateDonationRecordController implements DonationController{
    private final AddDonationRecordUsecase addDonationRecordUsecase;

    @Operation(
            summary = "기부 기록 생성 API",
            description = """
                    기부하기 이후에 기부 기록을 생성하기 위한 API입니다. 
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @PostMapping("/donate")
    public BaseResponse<Object> createDonationRecord(@CurrentUserId Long userId, @RequestBody CreateDonationRecordRequest createDonationRecordRequest) {
        AddDonationRecordCommand command = new AddDonationRecordCommand(
                userId,
                createDonationRecordRequest.donationId(),
                createDonationRecordRequest.donationAmount(),
                createDonationRecordRequest.isAnonymous(),
                createDonationRecordRequest.walletAddress()
        );

        addDonationRecordUsecase.addDonationRecord(command);
        return new BaseResponse<>(null);
    }
}
