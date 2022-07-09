package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "topics")
public class Topic extends AbsEntity {

    @FullTextField
    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    List<Collection> collections;
}
