package com.example.itransitioncourseproject.payloads.request.collection;

import com.example.itransitioncourseproject.payloads.request.FieldDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CollectionCreateDto {

    @NotBlank(message = "Collection name must not be blank")
    String name;

    String description;

    @NotNull(message = "Topic id must not be null")
    Long topicId;

    List<FieldDto> fieldDtoList;
}
