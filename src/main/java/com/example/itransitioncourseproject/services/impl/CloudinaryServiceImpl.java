package com.example.itransitioncourseproject.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.itransitioncourseproject.entities.CloudinaryResource;
import com.example.itransitioncourseproject.services.CloudinaryService;
import com.example.itransitioncourseproject.utils.CloudinaryConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public CloudinaryResource uploadFile(File file) throws IOException {
        Map map = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                "public_id", UUID.randomUUID().toString()
        ));
        return new CloudinaryResource(
                (String) map.get(CloudinaryConstants.FIELD_FORMAT),
                (String) map.get(CloudinaryConstants.FIELD_RESOURCE_TYPE),
                (String) map.get(CloudinaryConstants.FIELD_SECURE_URL),
                (String) map.get(CloudinaryConstants.FIELD_ORIGINAL_FILE_NAME)
        );
    }
}
