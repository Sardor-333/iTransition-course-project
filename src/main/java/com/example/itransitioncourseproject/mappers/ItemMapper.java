package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.Tag;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import com.example.itransitioncourseproject.repositories.TagRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {

    @Autowired
    private TagRepo tagRepo;

    @Mapping(expression = "java(src.getName())", target = "name")
    @Mapping(expression = "java(getItemTags(src.getTagIdList()))", target = "tags")
    @Mapping(expression = "java(collection)", target = "collection")
    public abstract Item mapFromCreateDtoToEntity(ItemCreateDto src, Collection collection);

    @Named("getItemTags")
    protected List<Tag> getItemTags(List<Long> tagIdList) {
        return tagIdList
                .stream()
                .map(tagId -> tagRepo.findById(tagId).orElseThrow(() -> new ObjectNotFoundException("Tag with id: " + tagId + " not found!")))
                .collect(Collectors.toList());
    }
}
