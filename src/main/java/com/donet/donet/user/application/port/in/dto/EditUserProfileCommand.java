package com.donet.donet.user.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class EditUserProfileCommand {
    private Long userId;
    private MultipartFile profileImage;
    private String nickname;
    private String walletAddress;
}
