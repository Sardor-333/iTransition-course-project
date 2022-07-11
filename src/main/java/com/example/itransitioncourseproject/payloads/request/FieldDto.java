package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FieldDto {

    @NotBlank(message = "Field name must not be blank")
    String name;

    @NotBlank(message = "Field type must not be blank")
    String type;
}
