package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import com.example.itransitioncourseproject.repositories.CollectionRepo;
import com.example.itransitioncourseproject.services.CollectionService;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public Paged<CollectionProjection> getCollections(Integer page, Integer size) {
        PageSizeUtils.validatePageAndSize(page, size);
        Page<CollectionProjection> collectionsPage = collectionRepo.getCollectionsPageable(PageRequest.of(page - 1, size));
        return new Paged<>(collectionsPage, Paging.of(collectionsPage.getTotalPages(), page, size));
    }
}
