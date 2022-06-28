package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "values")
public class Value extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    Field field;

    @Column(nullable = false)
    String targetValue;
}
