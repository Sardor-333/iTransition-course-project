package com.example.itransitioncourseproject.payloads.request.item;

import com.example.itransitioncourseproject.payloads.request.ValueDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

// Class to create and update Item
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

    @NotBlank(message = "Item name must not be blank")
    String name;

    Set<Long> tagIdList;

    List<ValueDto> valueDtoList;
}
