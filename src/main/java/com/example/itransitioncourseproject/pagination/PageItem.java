package com.example.itransitioncourseproject.pagination;

import com.example.itransitioncourseproject.enums.PageItemType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageItem {

    PageItemType pageItemType;
    int index;
    boolean active;
}
