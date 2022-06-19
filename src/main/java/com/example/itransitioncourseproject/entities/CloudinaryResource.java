package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
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

    @Column(nullable = false)
    String ext;

    @Column(nullable = false)
    String resourceType;

    @Column(nullable = false, updatable = false, unique = true)
    String secureUrl;

    @Column(nullable = false, updatable = false)
    String fileOriginalName;

    @OneToOne(mappedBy = "cloudinaryResource")
    Collection collection;

    @OneToOne(mappedBy = "photo")
    User user;
}
