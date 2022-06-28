package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.projections.TopicProjection;
import com.example.itransitioncourseproject.repositories.TopicRepo;
import com.example.itransitioncourseproject.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepo topicRepo;

    @Override
    public List<TopicProjection> getTopics() {
        return topicRepo.getTopics();
    }
}
