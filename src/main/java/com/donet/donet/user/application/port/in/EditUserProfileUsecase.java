package com.donet.donet.user.application.port.in;

import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;
import org.springframework.web.multipart.MultipartFile;

public interface EditUserProfileUsecase {
    void editProfile(EditUserProfileCommand command);

    void editProfileImage(Long userId, MultipartFile profileImage);

    void editNickname(Long userId, String nickname);

    void editWalletAddress(Long userId, String walletAddress);

}
