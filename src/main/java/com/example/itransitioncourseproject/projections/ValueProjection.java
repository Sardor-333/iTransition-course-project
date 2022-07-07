package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ValueProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getTargetValue();

    @Value("#{@fieldRepo.getFieldByValueId(target.id)}")
    FieldProjection getField();
}
