package com.example.itransitioncourseproject.pagination;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Paged<T> {

    Page<T> page;
    Paging paging;
}
