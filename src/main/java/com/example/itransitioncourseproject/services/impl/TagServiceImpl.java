package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.projections.TagProjection;
import com.example.itransitioncourseproject.repositories.TagRepo;
import com.example.itransitioncourseproject.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Override
    public List<TagProjection> getAllTags() {
        return tagRepo.getAllTags();
    }
}
