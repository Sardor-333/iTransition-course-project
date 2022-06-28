package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Field;
import com.example.itransitioncourseproject.enums.FieldType;
import com.example.itransitioncourseproject.payloads.request.FieldDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class FieldMapper {

    @Mapping(target = "fieldType", expression = "java(getFieldType(src.getType()))")
    public abstract Field mapFromCreateDtoToEntity(FieldDto src);

    @Named("getFieldType")
    public FieldType getFieldType(String type) {
        return FieldType.valueOf(type);
    }
}
