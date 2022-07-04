package com.example.itransitioncourseproject.payloads.request.item;

import com.example.itransitioncourseproject.payloads.request.ValueDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreateDto {

    // item name
    String name;

    List<Long> tagIdList;

    List<ValueDto> valueDtoList;
}
