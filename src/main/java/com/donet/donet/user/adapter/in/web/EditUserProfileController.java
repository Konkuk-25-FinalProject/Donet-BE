package com.donet.donet.user.adapter.in.web;

import com.donet.donet.global.annotation.CurrentUserId;
import com.donet.donet.global.response.BaseResponse;
import com.donet.donet.user.application.port.in.EditUserProfileUsecase;
import com.donet.donet.user.adapter.in.web.dto.EditUserProfileRequest;
import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class EditUserProfileController implements UserController{
    private final EditUserProfileUsecase editUserProfileUsecase;

    @PatchMapping("/users/me")
    public BaseResponse<Void> editUserProfile(@CurrentUserId Long userId,
                                                                @RequestPart(required = false) MultipartFile profileImage,
                                                                @Validated @RequestPart EditUserProfileRequest request){
        editUserProfileUsecase.editProfile(new EditUserProfileCommand(userId, profileImage, request.nickname(), request.walletAddress()));
        return new BaseResponse<>(null);
    }
}
