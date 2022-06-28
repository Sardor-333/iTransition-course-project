package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "cloudinary_resources")
public class CloudinaryResource extends AbsEntity {

    String extension;

    String resourceType;

    String secureUrl;

    String fileOriginalName;

    @OneToOne(mappedBy = "img")
    Collection collection;

    @OneToOne(mappedBy = "photo")
    User user;

    public CloudinaryResource(String extension, String resourceType, String secureUrl, String fileOriginalName) {
        this.extension = extension;
        this.resourceType = resourceType;
        this.secureUrl = secureUrl;
        this.fileOriginalName = fileOriginalName;
    }
}
