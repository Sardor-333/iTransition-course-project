package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.projections.TopicProjection;
import com.example.itransitioncourseproject.repositories.TopicRepo;
import com.example.itransitioncourseproject.services.TopicService;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepo topicRepo;

    @Override
    public List<TopicProjection> getAllTopics() {
        return topicRepo.getAllTopics();
    }

    @Override
    public Paged<TopicProjection> getTopicsPageable(Integer page, Integer size) {
        PageSizeUtils.validatePageAndSize(page, size, topicRepo);
        Page<TopicProjection> topicsPage = topicRepo.getTopicsPageable(PageRequest.of(page - 1, size));
        return new Paged<>(topicsPage, Paging.of(topicsPage.getTotalPages(), page, size));
    }
}
