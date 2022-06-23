package com.example.itransitioncourseproject.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public interface FileService {

    default void init(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    default boolean validateMultipart(MultipartFile multipartFile) {
        return !(multipartFile == null ||
                multipartFile.getOriginalFilename() == null ||
                multipartFile.getOriginalFilename().equals("") ||
                multipartFile.getSize() == 0 ||
                !this.isSupportedContentType(multipartFile));
    }

    File save(MultipartFile upload, String directoryPath) throws IOException;

    boolean isSupportedContentType(MultipartFile multipartFile);
}
