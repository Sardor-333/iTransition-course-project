package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.TagProjection;

import java.util.List;

public interface TagService {

    List<TagProjection> getAllTags();
}
