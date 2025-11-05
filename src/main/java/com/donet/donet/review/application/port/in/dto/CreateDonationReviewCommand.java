package com.donet.donet.review.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateDonationReviewCommand {
    private Long userId;
    private Long donationId;
    private String title;
    private String summary;
    private List<String> tags;
    private String content;
    private MultipartFile image;
}
