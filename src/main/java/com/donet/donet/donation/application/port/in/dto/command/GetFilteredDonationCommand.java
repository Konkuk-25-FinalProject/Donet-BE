package com.donet.donet.donation.application.port.in.dto.command;

import com.donet.donet.donation.domain.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public record GetFilteredDonationCommand(
        Long userId,
        List<String> categories,
        Pageable pageable
) {
}
