package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.tag.TagCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.TagProjection;

import java.util.List;

public interface TagService {

    List<TagProjection> getAllTags();

    Paged<TagProjection> getTagsPageable(Integer page, Integer size);

    ApiResponse createTag(TagCreateDto tagCreateDto);
}
