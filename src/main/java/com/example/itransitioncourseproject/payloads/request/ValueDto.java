package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValueDto {

    @NotBlank(message = "Field name must not be blank")
    String fieldName;

    @NotNull(message = "Field name must not be null")
    String fieldValue;
}
