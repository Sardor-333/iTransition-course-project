package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "collections")
public class Collection extends AbsEntity {

    @Column(nullable = false)
    String name;

    String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    CloudinaryResource cloudinaryResource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    User owner;

    @OneToMany(mappedBy = "collection")
    List<Item> items;

    @OneToMany(mappedBy = "collection")
    List<Field> fields;
}
