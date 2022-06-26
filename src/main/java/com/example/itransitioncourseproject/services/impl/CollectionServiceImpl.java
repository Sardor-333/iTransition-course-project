package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.repositories.CollectionRepo;
import com.example.itransitioncourseproject.services.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepo collectionRepo;

    @Override
    public List<CollectionProjection> getTop5LargestCollections() {
        return collectionRepo.getTop5BiggestCollections();
    }
}
