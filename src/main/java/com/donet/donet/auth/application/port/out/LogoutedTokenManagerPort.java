package com.donet.donet.auth.application.port.out;

public interface LogoutedTokenManagerPort {
    void add(String accessToken);

    boolean isLogouted(String accessToken);
}
