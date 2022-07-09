package com.example.itransitioncourseproject.payloads.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchDto {

    String searchKey;

    int fetch = 20;
}
