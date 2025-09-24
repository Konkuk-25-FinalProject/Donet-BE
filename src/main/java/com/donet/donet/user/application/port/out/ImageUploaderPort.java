package com.donet.donet.user.application.port.out;

import com.donet.donet.global.infra.aws.FileUploadingFailedException;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploaderPort {
    String upload(MultipartFile profileImage) throws FileUploadingFailedException;
}
