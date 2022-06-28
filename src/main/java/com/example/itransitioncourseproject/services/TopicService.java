package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.TopicProjection;

import java.util.List;

public interface TopicService {

    List<TopicProjection> getTopics();
}
