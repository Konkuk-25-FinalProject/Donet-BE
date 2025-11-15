package com.donet.donet.review.application;

import com.donet.donet.donation.application.port.out.FindDonationPort;
import com.donet.donet.donation.domain.Donation;
import com.donet.donet.review.application.port.in.dto.CreateDonationReviewCommand;
import com.donet.donet.review.application.port.out.SaveDonationReviewPort;
import com.donet.donet.review.domain.DonationReview;
import com.donet.donet.user.application.port.out.FindUserPort;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import com.donet.donet.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreateDonationReviewServiceTest {
    @InjectMocks
    private CreateDonationReviewService service;

    @Mock
    private FindUserPort findUserPort;

    @Mock
    private FindDonationPort findDonationPort;

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

        User mockUser = mock(User.class);
        Donation mockDonation = mock(Donation.class);
        given(mockDonation.isWriter(mockUser)).willReturn(true);

        given(findUserPort.findById(anyLong())).willReturn(Optional.of(mockUser));
        given(findDonationPort.findDonationById(anyLong())).willReturn(mockDonation);
        given(saveDonationReviewPort.save(any())).willReturn(returnedReview);

        // when
        DonationReview review = service.create(command);

        // then
        assertThat(review).isEqualTo(returnedReview);
    }

}