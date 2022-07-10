package com.example.itransitioncourseproject.payloads.request.item;

import com.example.itransitioncourseproject.payloads.request.ValueCreateDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreateDto {

    // todo : validation

    // item name
    String name;

    Set<Long> tagIdList;

    List<ValueCreateDto> valueCreateDtoList;
}
