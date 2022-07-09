package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

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

    @FullTextField
    @Column(nullable = false)
    String name;

    @FullTextField
    @Column(columnDefinition = "TEXT")
    String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    CloudinaryResource img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    User user;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    List<Item> items;

    @IndexedEmbedded
    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    List<Field> fields;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Topic topic;
}
