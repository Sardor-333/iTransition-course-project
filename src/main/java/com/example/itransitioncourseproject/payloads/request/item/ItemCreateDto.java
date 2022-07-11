package com.example.itransitioncourseproject.payloads.request.item;

import com.example.itransitioncourseproject.payloads.request.ValueDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemCreateDto {

    @NotBlank(message = "Item name must not be blank")
    String name;

    Set<Long> tagIdList;

    List<@Valid ValueDto> valueDtoList;
}
