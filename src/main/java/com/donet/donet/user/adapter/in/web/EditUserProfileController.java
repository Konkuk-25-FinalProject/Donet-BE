package com.donet.donet.user.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.global.swagger.CustomExceptionDescription;
import com.donet.donet.user.application.port.in.EditUserProfileUsecase;
import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.donet.donet.global.swagger.SwaggerResponseDescription.DEFAULT;
import static com.donet.donet.global.swagger.SwaggerResponseDescription.EDIT_USER_PROFILE;

@RestController
@RequiredArgsConstructor
public class EditUserProfileController implements UserController{
    private final EditUserProfileUsecase editUserProfileUsecase;

    @Operation(
            summary = "온보딩 유저 프로필 등록 API",
            description = """
                    유저의 프로필 이미지, 닉네임, 지갑주소를 등록할 수 있다. 
                    프로필 이미지를 변경하지 않으려면 요청에 포함하지 않아야 한다. 
                    닉네임과 지갑주소는 필수 값이라서 기존 값을 그대로 보내야 한다
                    """
    )
    @PostMapping(value = "/users/me", consumes = {"multipart/form-data"})
    public BaseResponse<Void> editUserProfile(@Parameter(hidden = true) @CurrentUserId Long userId,
                                              @Parameter(name = "변경할 프로필 이미지", required = false) @RequestPart(required = false) MultipartFile profileImage,
                                              @Parameter(name = "변경하거나 유지할 닉네임", required = true) @NotBlank String nickname,
                                              @Parameter(name= "변경하거나 유지할 지갑주소", required = true) @NotBlank String walletAddress
    ){
        editUserProfileUsecase.editProfile(new EditUserProfileCommand(userId, profileImage, nickname, walletAddress));
        return new BaseResponse<>(null);
    }

    @Operation(
            summary = "유저 프로필 이미지 수정 API",
            description = """
                    유저의 프로필 이미지를 수정할 수 있다. 
                    """
    )
    @CustomExceptionDescription(EDIT_USER_PROFILE)
    @PatchMapping(value = "/users/me/profile-image", consumes = {"multipart/form-data"})
    public BaseResponse<Void> editUserProfileImage(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                   @Parameter(name = "변경할 프로필 이미지") @RequestPart MultipartFile profileImage
    ){
        editUserProfileUsecase.editProfileImage(userId, profileImage);
        return new BaseResponse<>(null);
    }

    @Operation(
            summary = "유저 닉네임 수정 API",
            description = """
                    유저의 닉네임을 수정할 수 있다. 
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @PatchMapping(value = "/users/me/nickname")
    public BaseResponse<Void> editUserNickname(@Parameter(hidden = true) @CurrentUserId Long userId,
                                               @Parameter(name = "변경할 닉네임", required = true) @NotBlank String nickname
    ){
        editUserProfileUsecase.editNickname(userId, nickname);
        return new BaseResponse<>(null);
    }

    @Operation(
            summary = "유저 지갑주소 수정 API",
            description = """
                    유저의 지갑주소를 수정할 수 있다.
                    """
    )
    @CustomExceptionDescription(DEFAULT)
    @PatchMapping(value = "/users/me/wallet-address")
    public BaseResponse<Void> editUserWalletAddress(@Parameter(hidden = true) @CurrentUserId Long userId,
                                                    @Parameter(name= "변경할 지갑주소", required = true) @NotBlank String walletAddress
    ){
        editUserProfileUsecase.editWalletAddress(userId, walletAddress);
        return new BaseResponse<>(null);
    }
}
