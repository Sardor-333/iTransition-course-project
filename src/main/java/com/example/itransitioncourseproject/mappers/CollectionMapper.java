package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Field;
import com.example.itransitioncourseproject.entities.Topic;
import com.example.itransitioncourseproject.payloads.request.CollectionDto;
import com.example.itransitioncourseproject.payloads.request.FieldDto;
import com.example.itransitioncourseproject.repositories.TopicRepo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CollectionMapper {

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private FieldMapper fieldMapper;

    @Mapping(target = "topic", expression = "java(getTopic(src.getTopicId()))")
    @Mapping(target = "fields", expression = "java(getFields(src.getFieldDtoList()))")
    public abstract Collection mapFromCreateDtoToEntity(CollectionDto src);

    @Named("getTopic")
    public Topic getTopic(Long topicId) {
        return topicRepo.findById(topicId).orElse(null);
    }

    @Named("getFields")
    public List<Field> getFields(List<FieldDto> fieldDtoList) {
        if (fieldDtoList == null) return null;

        List<Field> fields = new ArrayList<>();
        for (FieldDto fieldDto : fieldDtoList) {
            Field field = fieldMapper.mapFromCreateDtoToEntity(fieldDto);
            fields.add(field);
        }
        return fields;
    }
}
