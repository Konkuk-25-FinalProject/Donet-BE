package com.donet.donet.user.application.port.in;

import com.donet.donet.user.application.port.in.dto.EditUserProfileCommand;

public interface EditUserProfileUsecase {
    void editProfile(EditUserProfileCommand command);
}
