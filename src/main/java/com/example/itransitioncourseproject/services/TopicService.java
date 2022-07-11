package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.projections.TopicProjection;

import java.util.List;

public interface TopicService {

    List<TopicProjection> getAllTopics();

    Paged<TopicProjection> getTopicsPageable(Integer page, Integer size);
}
