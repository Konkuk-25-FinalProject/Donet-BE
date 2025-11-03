package com.donet.donet.review.application;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.global.exception.CustomException;
import com.donet.donet.global.infra.aws.FileUploadingFailedException;
import com.donet.donet.review.application.port.in.dto.CreateDonationReviewCommand;
import com.donet.donet.review.application.port.in.CreateDonationReviewUsecase;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import com.donet.donet.user.application.port.out.UserRepositoryPort;
import com.donet.donet.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.donet.donet.global.response.status.BaseExceptionResponseStatus.*;

@Service
@RequiredArgsConstructor
public class CreateDonationReviewService implements CreateDonationReviewUsecase {
    private final UserRepositoryPort userRepositoryPort;
    private final FindDonationPort findDonationPort;
    private final ImageUploaderPort imageUploaderPort;
    private final SaveDonationReviewPort saveDonationReviewPort;

    @Override
    public DonationReview create(CreateDonationReviewCommand command) {
        User user = userRepositoryPort.findById(command.getUserId()).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        Donation donation = findDonationPort.findDonationById(command.getDonationId());

        if(!donation.isWriter(user)){
            throw new CustomException(BAD_REQUEST);
        }

        String imageUrl = uploadImage(command);
        DonationReview donationReview = new DonationReview(command, imageUrl);
        return saveDonationReviewPort.save(donationReview);
    }

    private String uploadImage(CreateDonationReviewCommand command) {
        if(command.getImage() == null) {
            return null;
        }
        try{
            return imageUploaderPort.upload(command.getImage());
        }
        catch (FileUploadingFailedException e){
            throw new CustomException(REVIEW_IMAGE_UPLOAD_FAILED);
        }
    }
}
