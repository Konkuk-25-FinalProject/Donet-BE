package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.application.port.in.dto.response.GetFilteredDonationResponse;
import com.donet.donet.donation.application.port.in.GetFilteredDonationUsecase;
import com.donet.donet.donation.application.port.in.dto.command.GetFilteredDonationCommand;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class GetDonationListController implements DonationController{
    private final GetFilteredDonationUsecase getFilteredDonationUsecase;

    @Operation(
            summary = "기부 목록 조회 API",
            description = """
                    추천, 마감 임박, 인기있는 기부 조회 및 기부 필터링 페이지네이션
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @GetMapping()
    public BaseResponse<GetFilteredDonationResponse> getDonationList(@CurrentUserId Long userId, @RequestParam("category")List<String> categories, @ParameterObject Pageable pageable) {
        GetFilteredDonationCommand command = new GetFilteredDonationCommand(userId, categories, pageable);

        return new BaseResponse<>(getFilteredDonationUsecase.getFilteredDonation(command));
    }
}
