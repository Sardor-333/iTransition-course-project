package com.example.itransitioncourseproject.payloads.request.topic;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicCreateDto {

    @NotBlank(message = "Topic name must not be blank")
    String name;
}
