package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.CloudinaryResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface MultipartService {

    default void initFileOrDirectory(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    default boolean isValidMultipart(MultipartFile multipartFile) {
        return multipartFile != null
                && !multipartFile.isEmpty()
                && multipartFile.getOriginalFilename() != null
                && !multipartFile.getOriginalFilename().equals("")
                && multipartFile.getSize() != 0
                && this.isSupportedContentType(multipartFile);
    }

    boolean isSupportedContentType(MultipartFile multipartFile);

    CloudinaryResource generateCloudinaryResourceFromMultipart(MultipartFile multipartFile) throws IOException;
}
