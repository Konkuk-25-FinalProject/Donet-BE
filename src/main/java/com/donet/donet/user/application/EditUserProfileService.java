package com.donet.donet.user.application;

import com.donet.donet.global.exception.CustomException;
import com.donet.donet.global.infra.aws.FileUploadingFailedException;
import com.donet.donet.user.application.out.CreateUserPort;
import com.donet.donet.user.application.port.in.EditUserProfileUsecase;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import com.donet.donet.user.application.port.out.FindUserPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.PROFILE_IMG_UPLOADING_FAILED;
import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class EditUserProfileService implements EditUserProfileUsecase {
    private final FindUserPort findUserPort;
    private final CreateUserPort createUserPort;
    private final ImageUploaderPort imageUploaderPort;
    @Override
    public void editProfile(EditUserProfileCommand command) {
        log.info("[editProfile] userId = {}", command.getUserId());

        User user = findUserPort.findById(command.getUserId())
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        if(command.getProfileImage() != null){
            log.info("[editProfile] userId = {} 프로필 변경", command.getUserId());
            try{
                String profileImageUrl = imageUploaderPort.upload(command.getProfileImage());
                user.editProfileImage(profileImageUrl);
            }
            catch (FileUploadingFailedException e){
                throw new CustomException(PROFILE_IMG_UPLOADING_FAILED);
            }
        }
        user.editProfileData(command.getNickname(), command.getWalletAddress());

        createUserPort.save(user);
    }
}
