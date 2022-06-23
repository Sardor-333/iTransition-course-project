package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileSaveDto {

    String firstName;
    String lastName;
    String username;
}
