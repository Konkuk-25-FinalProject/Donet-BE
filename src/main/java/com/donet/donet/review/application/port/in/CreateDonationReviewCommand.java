package com.donet.donet.review.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Getter
public class CreateDonationReviewCommand {
    private String title;
    private String summary;
    private List<String> tags;
    private String content;
    private MultipartFile image;
}
