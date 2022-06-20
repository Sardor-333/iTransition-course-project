package com.example.itransitioncourseproject.entities.abs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    @UpdateTimestamp
    protected LocalDateTime updatedAt = LocalDateTime.now();

    protected Long createdBy;

    protected Long updatedBy;
}
