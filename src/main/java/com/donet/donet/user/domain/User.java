package com.donet.donet.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private Long id;
    private String nickname;
    private String profileImage;
    private String loginProvider;
    private String loginId;
}
