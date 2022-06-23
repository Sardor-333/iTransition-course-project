package com.example.itransitioncourseproject.mappers.base;

public interface BaseMapper<E, CD, UD> {

    E mapFromCreateDtoToEntity(CD dto);

    void mapFromUpdateDtoToEntity(UD src, E target);
}
