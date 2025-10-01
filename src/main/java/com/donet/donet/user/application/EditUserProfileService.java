package com.donet.donet.user.application;

import com.donet.donet.global.exception.CustomException;
import com.donet.donet.global.infra.aws.FileUploadingFailedException;
import com.donet.donet.user.application.port.in.EditUserProfileUsecase;
import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import com.donet.donet.user.application.port.out.UserRepositoryPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.PROFILE_IMG_UPLOADING_FAILED;
import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class EditUserProfileService implements EditUserProfileUsecase {
    private final UserRepositoryPort userRepositoryPort;
    private final ImageUploaderPort imageUploaderPort;

    @Override
    public void editProfile(EditUserProfileCommand command) {
        log.info("[editProfile] userId = {}", command.getUserId());

        User user = userRepositoryPort.findById(command.getUserId())
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
        user.editNickname(command.getNickname());
        user.editWalletAddress(command.getWalletAddress());

        userRepositoryPort.save(user);
    }

    @Override
    public void editProfileImage(Long userId, MultipartFile profileImage) {
        log.info("[editProfileImage] userId = {}", userId);

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        try{
            String profileImageUrl = imageUploaderPort.upload(profileImage);
            user.editProfileImage(profileImageUrl);
        }
        catch (FileUploadingFailedException e){
            throw new CustomException(PROFILE_IMG_UPLOADING_FAILED);
        }

        userRepositoryPort.save(user);
    }

    @Override
    public void editNickname(Long userId, String nickname) {
        log.info("[editNickname] userId = {}", userId);

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        user.editNickname(nickname);

        userRepositoryPort.save(user);
    }

    @Override
    public void editWalletAddress(Long userId, String walletAddress) {
        log.info("[editWalletAddress] userId = {}", userId);

        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));

        user.editWalletAddress(walletAddress);

        userRepositoryPort.save(user);
    }
}
