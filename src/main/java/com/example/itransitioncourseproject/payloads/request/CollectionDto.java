package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionDto {

    String name;
    String description;
    Long topicId;

    List<FieldDto> fieldDtoList;
}
