package com.donet.donet.user.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.user.application.port.in.EditUserProfileUsecase;
import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class EditUserProfileController implements UserController{
    private final EditUserProfileUsecase editUserProfileUsecase;

    @Operation(
            summary = "유저 프로필 수정 API",
            description = """
                    유저의 프로필 이미지, 닉네임, 지갑주소를 수정할 수 있다. 
                    프로필 이미지를 변경하지 않으려면 요청에 포함하지 않아야 한다. 
                    닉네임과 지갑주소는 필수 값이라서 기존 값을 그대로 보내야 한다
                    """
    )
    @PatchMapping(value = "/users/me", consumes = {"multipart/form-data"})
    public BaseResponse<Void> editUserProfile(@Parameter(hidden = true) @CurrentUserId Long userId,
                                              @Parameter(name = "변경할 프로필 이미지", required = false) @RequestPart(required = false) MultipartFile profileImage,
                                              @Parameter(name = "변경하거나 유지할 닉네임", required = true) @NotBlank String nickname,
                                              @Parameter(name= "변경하거나 유지할 지갑주소", required = true) @NotBlank String walletAddress
                                              ){
        editUserProfileUsecase.editProfile(new EditUserProfileCommand(userId, profileImage, nickname, walletAddress));
        return new BaseResponse<>(null);
    }
}
