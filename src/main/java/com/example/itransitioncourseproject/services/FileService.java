package com.example.itransitioncourseproject.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {

    File save(MultipartFile upload, String directoryPath) throws IOException;
}
