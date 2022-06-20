package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.projections.CollectionProjection;

public interface CollectionService {

    Paged<CollectionProjection> getCollections(Integer page, Integer size);
}
