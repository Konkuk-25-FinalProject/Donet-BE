package com.donet.donet.donation.adapter.in.web;

import com.donet.donet.donation.adapter.in.web.dto.CreateDonationRequest;
import com.donet.donet.donation.adapter.in.web.dto.CreateDonationResponse;
import com.donet.donet.donation.application.port.in.CreateDonationUsecase;
import com.donet.donet.donation.application.port.in.dto.command.CreateDonationCommand;
import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/donation")
public class CreateDonationController implements DonationController{
    private final CreateDonationUsecase createDonationUsecase;

    @Operation(
            summary = "새로운 기부 생성 API",
            description = """
                    새로운 기부를 생성하는 API입니다. 기부 등록 페이지에서 사용됩니다.  
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @PostMapping(path = "/register" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse<CreateDonationResponse> createDonation(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                               @Parameter @RequestPart(required = false) List<MultipartFile> images,
                                                               @Parameter @RequestPart CreateDonationRequest request
    ){
        CreateDonationCommand command = CreateDonationCommand.from(
                userId,
                images,
                request.title(),
                request.isAnonymous(),
                request.toCommandItem(),
                request.startDate(),
                request.endDate(),
                request.category(),
                request.targetAmount(),
                request.partnerId(),
                request.content()
        );

        return new BaseResponse<>(createDonationUsecase.createDonation(command));
    }
}
