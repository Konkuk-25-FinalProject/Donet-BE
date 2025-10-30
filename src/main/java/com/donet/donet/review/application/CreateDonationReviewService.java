package com.donet.donet.review.application;

import com.donet.donet.review.application.port.in.CreateDonationReviewCommand;
import com.donet.donet.review.application.port.in.CreateDonationReviewUsecase;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDonationReviewService implements CreateDonationReviewUsecase {
    private final ImageUploaderPort imageUploaderPort;
    private final SaveDonationReviewPort saveDonationReviewPort;

    @Override
    public DonationReview create(CreateDonationReviewCommand command) {
        String imageUrl = null;
        if(command.getImage() != null){
            imageUrl = imageUploaderPort.upload(command.getImage());
        }
        DonationReview donationReview = new DonationReview(command, imageUrl);
        return saveDonationReviewPort.save(donationReview);
    }
}
