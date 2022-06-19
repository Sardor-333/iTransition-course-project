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

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id")
    Item item;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false, referencedColumnName = "id")
    Field field;

    @Column(nullable = false)
    String targetValue;
}
