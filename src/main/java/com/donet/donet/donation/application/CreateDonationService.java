package com.donet.donet.donation.application;

import com.donet.donet.donation.application.port.in.CreateDonationUsecase;
import com.donet.donet.donation.application.port.in.dto.command.CreateDonationCommand;
import com.donet.donet.donation.application.port.out.CreateDonationPort;
import com.donet.donet.donation.domain.Category;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.donation.domain.DonationItem;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateDonationService implements CreateDonationUsecase {
    private final CreateDonationPort createDonationPort;
    private final ImageUploaderPort imageUploaderPort;

    @Override
    public void createDonation(CreateDonationCommand command) {
        List<String> imagesUrls = command.images()
                .stream()
                .map(imageUploaderPort::upload)
                .toList();

        List<DonationItem> donationItems = command.items()
                .stream()
                .map(item -> DonationItem.builder()
                        .name(item.itemName())
                        .quantity(item.quantity())
                        .price(item.price())
                        .build()
                )
                .toList();

        List<Category> categories = command.category()
                .stream()
                .map(category -> Category.builder()
                        .name(category)
                        .build()
                )
                .toList();

        Donation donation = Donation.builder()
                .title(command.title())
                .description(command.content())
                .anonymous(command.isAnonymous())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .targetAmount(command.targetAmount())
                .currentAmount(0L)
                .views(0L)
                .imageUrl(imagesUrls)
                .userId(command.userId())
                .partnerId(command.partnerId())
                .donationItems(donationItems)
                .categories(categories)
                .build();

        createDonationPort.createDonation(donation);
    }
}
