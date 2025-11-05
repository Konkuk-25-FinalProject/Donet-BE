package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.GetPartnerUsecase;
import com.donet.donet.donation.application.port.in.dto.response.GetPartnerResponse;
import com.donet.donet.donation.application.port.out.FindPartnerPort;
import com.donet.donet.donation.domain.Partner;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partners")
public class GetParnterController implements DonationController{
    private final FindPartnerPort findPartnerPort;

    @Operation(
            summary = "파트너사 목록 조회 API",
            description = """
                    현재 Donet과 파트너십을 맺은 파트너사 목록을 조회하는 API입니다. 
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @GetMapping()
    public BaseResponse<GetPartnerResponse> getParnters() {
        List<Partner> partners = findPartnerPort.findAllPartners();
        return new BaseResponse<>(GetPartnerResponse.from(partners));
    }
}
