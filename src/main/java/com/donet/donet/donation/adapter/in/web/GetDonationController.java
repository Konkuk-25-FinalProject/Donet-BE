package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.GetDonationDetailUsecase;
import com.donet.donet.donation.application.port.in.dto.response.GetDonationDetailResponse;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class GetDonationController implements DonationController{
    private final GetDonationDetailUsecase getDonationDetailUsecase;

    @Operation(
            summary = "기부 상세 조회 API",
            description = """
                    기부 상세 정보를 조회할 수 있습니다.
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @GetMapping("/{donationId}/detail")
    public BaseResponse<GetDonationDetailResponse> getDonationDetail(@PathVariable long donationId) {
        return new BaseResponse<>(getDonationDetailUsecase.getDonationDetail(donationId));
    }
}
