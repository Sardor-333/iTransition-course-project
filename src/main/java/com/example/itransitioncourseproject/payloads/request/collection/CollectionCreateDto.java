package com.example.itransitioncourseproject.payloads.request.collection;

import com.example.itransitioncourseproject.payloads.request.FieldDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionCreateDto {
    // Todo : validation
    String name;
    String description;
    Long topicId;

    List<FieldDto> fieldDtoList;
}
