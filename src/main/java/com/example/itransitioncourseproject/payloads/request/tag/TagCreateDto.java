package com.example.itransitioncourseproject.payloads.request.tag;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagCreateDto {

    @NotBlank(message = "Tag name must not be blank")
    String name;
}
