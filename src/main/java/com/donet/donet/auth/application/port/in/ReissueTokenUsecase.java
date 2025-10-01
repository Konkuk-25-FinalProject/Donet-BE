package com.donet.donet.auth.application.port.in;

import com.donet.donet.auth.application.port.in.dto.ReissueTokenCommand;
import com.donet.donet.auth.application.port.in.dto.ReissueTokenResponse;

public interface ReissueTokenUsecase {
    ReissueTokenResponse reissue(ReissueTokenCommand command);
}
