package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.repositories.CollectionRepo;
import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepo collectionRepo;

    @Override
    public Paged<CollectionProjection> getCollections(Integer page, Integer size) {
        Validator.validatePageAndSize(page, size);
        Page<CollectionProjection> collectionsPage = collectionRepo.getCollectionsPageable(PageRequest.of(page - 1, size));
        return new Paged<>(collectionsPage, Paging.of(collectionsPage.getTotalPages(), page, size));
    }
}
