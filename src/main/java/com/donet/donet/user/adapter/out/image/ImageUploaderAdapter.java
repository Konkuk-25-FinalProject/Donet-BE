package com.donet.donet.user.adapter.out.image;

import com.donet.donet.global.infra.aws.FileUploadingFailedException;
import com.donet.donet.global.infra.aws.S3ImageUploader;
import com.donet.donet.user.application.port.out.ImageUploaderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class ImageUploaderAdapter implements ImageUploaderPort {
    private final S3ImageUploader s3ImageUploader;

    @Override
    public String upload(MultipartFile profileImage) throws FileUploadingFailedException {
        return s3ImageUploader.upload(profileImage);
    }
}
