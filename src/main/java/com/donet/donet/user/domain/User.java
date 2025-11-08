package com.donet.donet.user.domain;

import com.donet.donet.global.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_DOMAIN_RULE_VIOLATION;

@AllArgsConstructor
@Getter
@Builder
public class User {
    private Long id;
    private String nickname;
    private String profileImage;
    private String loginProvider;
    private String loginId;
    private String walletAddress;

    public void editProfileImage(String profileImage) {
        if(profileImage == null || profileImage.isBlank()){
            throw new CustomException(USER_DOMAIN_RULE_VIOLATION);
        }
        this.profileImage = profileImage;
    }

    public void editNickname(String nickname) {
        if(nickname == null || nickname.isBlank()){
            throw new CustomException(USER_DOMAIN_RULE_VIOLATION);
        }
        this.nickname = nickname;
    }

    public void editWalletAddress(String walletAddress) {
        if(walletAddress == null || walletAddress.isBlank()){
            throw new CustomException(USER_DOMAIN_RULE_VIOLATION);
        }
        this.walletAddress = walletAddress;
    }


}
