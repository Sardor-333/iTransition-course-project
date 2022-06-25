package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileDto {

    @NotBlank(message = "first name must not be blank")
    String firstName;

    @NotBlank(message = "last name must not be blank")
    String lastName;

    @NotBlank(message = "username name must not be blank")
    String username;

    MultipartFile profileImg;
}
