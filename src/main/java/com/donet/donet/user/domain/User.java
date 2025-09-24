package com.donet.donet.user.domain;

import com.donet.donet.global.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.USER_DOMAIN_RULE_VIOLATION;

@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private String nickname;
    private String profileImage;
    private String loginProvider;
    private String loginId;
    private String walletAddress;

    public void editProfileImage(String profileImage) {
        if(profileImage == null){
            throw new CustomException(USER_DOMAIN_RULE_VIOLATION);
        }
        this.profileImage = profileImage;
    }

    public void editProfileData(String nickname, String walletAddress) {
        if(nickname == null || nickname.isBlank() || walletAddress == null || walletAddress.isBlank()){
            throw new CustomException(USER_DOMAIN_RULE_VIOLATION);
        }
        this.nickname = nickname;
        this.walletAddress = walletAddress;
    }
}
