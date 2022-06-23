package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.services.FileService;
import org.apache.http.entity.ContentType;
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

    @Override
    public File save(MultipartFile multipartFile, String directoryPath) throws IOException {
        if (!validateMultipart(multipartFile))
            return null;

        Path directory = Paths.get(directoryPath);
        this.init(directory);

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

    @Override
    public boolean isSupportedContentType(MultipartFile multipartFile) {
        if (multipartFile == null) return false;
        String contentType = multipartFile.getContentType();
        return contentType != null && (contentType.equals(ContentType.IMAGE_PNG.getMimeType()) || contentType.equals(ContentType.IMAGE_JPEG.getMimeType()));
    }
}
