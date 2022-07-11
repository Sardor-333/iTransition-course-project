package com.example.itransitioncourseproject.payloads.request.collection;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionEditDto {

    @NotBlank(message = "Collection name must not be blank")
    String name;

    String description;

    @NotNull(message = "Topic id must not be null")
    Long topicId;
}
