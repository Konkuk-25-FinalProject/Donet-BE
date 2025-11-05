package com.donet.donet.review.application;

import com.donet.donet.review.application.port.in.GetDonationReviewsUsecase;
import com.donet.donet.review.application.port.in.dto.GetDonationReviewsResponse;
import com.donet.donet.review.application.port.out.LoadDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDonationReviewsService implements GetDonationReviewsUsecase {
    private final LoadDonationReviewPort loadDonationReviewPort;
    @Override
    public GetDonationReviewsResponse getDonationReviews(int size, Long lastId) {
        List<DonationReview> reviews = loadDonationReviewPort.findPage(size + 1, lastId);
        Boolean hasNext = reviews.size() > size;
        Long nextLastId =  null;

        List<GetDonationReviewsResponse.ReviewSummary> summaries = reviews.stream()
                .limit(size)
                .map(entity -> new GetDonationReviewsResponse.ReviewSummary(entity.getId(),
                        entity.getTitle(),
                        entity.getTags(),
                        entity.getWriterName(),
                        entity.getImageUrl(),
                        entity.getContent()))
                .toList();

        if(summaries.size() > 0){
            nextLastId = summaries.get(summaries.size() - 1).donationReviewId();
        }

        return new GetDonationReviewsResponse(summaries, nextLastId, hasNext);
    }
}
