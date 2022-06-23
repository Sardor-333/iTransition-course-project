package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {

    @NotBlank(message = "First name mustn't be blank!")
    String firstName;

    @NotBlank(message = "Last name mustn't be blank!")
    String lastName;

    @NotBlank(message = "Username mustn't be blank!")
    @Min(value = 6, message = "Username length must be at least 6!")
    String username;

    @NotBlank(message = "Password mustn't be blank!")
    @Min(value = 6, message = "Password length must be at least 6")
    String password;

    MultipartFile profileImg;
}
