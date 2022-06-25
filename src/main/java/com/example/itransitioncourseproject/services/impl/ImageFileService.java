package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.services.FileService;
import com.example.itransitioncourseproject.services.MultipartService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageFileService implements FileService {

    private final MultipartService multipartService;

    public ImageFileService(@Lazy MultipartService multipartService) {
        this.multipartService = multipartService;
    }

    @Override
    public File save(MultipartFile multipartFile, String directoryPath) throws IOException {
        if (!multipartService.isValidMultipart(multipartFile))
            return null;

        Path directory = Paths.get(directoryPath);
        multipartService.initFileOrDirectory(directory);

        String[] split = multipartFile.getOriginalFilename().split("\\.");
        if (split.length >= 2) {
            String fileName = directoryPath + UUID.randomUUID() + "." + split[split.length - 1];
            File file = new File(fileName);
            boolean created = file.createNewFile();
            if (created) {
                byte[] targetBytes = multipartFile.getBytes();
                OutputStream out = new FileOutputStream(file);
                out.write(targetBytes);
                return file;
            }
        }
        return null;
    }
}
