package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.CloudinaryResource;

import java.io.File;
import java.io.IOException;

public interface CloudinaryService {

    CloudinaryResource uploadFile(File file) throws IOException;
}
