package com.donet.donet.review.application;

import com.donet.donet.review.application.port.in.CreateDonationReviewCommand;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreateDonationReviewServiceTest {
    @InjectMocks
    private CreateDonationReviewService service;

    @Mock
    private ImageUploaderPort imageUploaderPort;

    @Mock
    private SaveDonationReviewPort saveDonationReviewPort;

    @DisplayName("생성에 성공하면 기부후기를 반환한다")
    @Test
    void shouldReturnDonationReview(){
        // given
        CreateDonationReviewCommand command = mock(CreateDonationReviewCommand.class);
        DonationReview returnedReview = mock(DonationReview.class);
        given(imageUploaderPort.upload(any())).willReturn("image");
        given(saveDonationReviewPort.save(any())).willReturn(returnedReview);

        // when
        DonationReview review = service.create(command);

        // then
        assertThat(review).isEqualTo(returnedReview);
    }

}