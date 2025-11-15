package com.donet.donet.user.application.port.out;

import com.donet.donet.user.domain.User;

public interface CreateUserPort {
    User save(User user);
}
