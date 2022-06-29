package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Topic;
import com.example.itransitioncourseproject.payloads.request.collection.CollectionCreateDto;
import com.example.itransitioncourseproject.repositories.TopicRepo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class CollectionMapper {

    @Autowired
    private TopicRepo topicRepo;

    @Autowired
    private FieldMapper fieldMapper;

    @Mapping(target = "topic", expression = "java(getTopic(src.getTopicId()))")
    public abstract Collection mapFromCreateDtoToEntity(CollectionCreateDto src);

    @Named("getTopic")
    public Topic getTopic(Long topicId) {
        return topicRepo.findById(topicId).orElse(null);
    }
}
