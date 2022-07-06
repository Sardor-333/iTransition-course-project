package com.example.itransitioncourseproject.payloads.request.item;

import com.example.itransitioncourseproject.payloads.request.ValueCreateDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreateDto {

    // todo : validation

    // item name
    String name;

    List<Long> tagIdList;

    List<ValueCreateDto> valueCreateDtoList;
}
