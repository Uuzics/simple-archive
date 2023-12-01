package org.uuzics.simplearchive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.uuzics.simplearchive.common.S3Config;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

@Controller
public class FileController {
    @Autowired
    S3Presigner s3Presigner;

    @Autowired
    S3Config s3Config;

    @GetMapping("/file/{fileSlug}")
    public String handleS3FileDownload(@PathVariable("fileSlug") String fileSlug) {
        String bucket = s3Config.getBucket();
        long durationMinutes = s3Config.getPresignDuration();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileSlug)
                .build();
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(durationMinutes))
                .getObjectRequest(getObjectRequest)
                .build();
        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        String presignedUrl = presignedGetObjectRequest.url().toString();
        return "redirect:" + presignedUrl;
    }
}
