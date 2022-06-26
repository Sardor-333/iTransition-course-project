package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.CloudinaryResource;
import com.example.itransitioncourseproject.services.CloudinaryService;
import com.example.itransitioncourseproject.services.FileService;
import com.example.itransitioncourseproject.services.MultipartService;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MultipartServiceImpl implements MultipartService {

    private final FileService fileService;
    private final CloudinaryService cloudinaryService;

    @Override
    public boolean isSupportedContentType(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        return contentType != null && (contentType.equals(ContentType.IMAGE_PNG.getMimeType()) || contentType.equals(ContentType.IMAGE_JPEG.getMimeType()));
    }

    @Override
    public CloudinaryResource generateCloudinaryResourceFromMultipart(MultipartFile multipartFile) throws IOException {
        if (this.isValidMultipart(multipartFile)) {
            File saved = fileService.save(multipartFile, MultipartService.DIRECTORY_UPLOAD);
            CloudinaryResource cloudinaryResource = cloudinaryService.uploadFile(saved);
            boolean deleted = saved.delete();
            return cloudinaryResource;
        }
        return null;
    }
}
