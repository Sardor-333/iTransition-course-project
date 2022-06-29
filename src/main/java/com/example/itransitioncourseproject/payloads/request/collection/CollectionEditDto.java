package com.example.itransitioncourseproject.payloads.request.collection;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionEditDto {
    // Todo : validation
    String name;
    String description;
    Long topicId;
}
