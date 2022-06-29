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
@Table(name = "topics")
public class Topic extends AbsEntity {

    @Column(nullable = false, unique = true)
    String name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    List<Collection> collections;
}
