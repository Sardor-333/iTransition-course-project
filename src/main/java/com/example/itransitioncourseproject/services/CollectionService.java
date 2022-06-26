package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.CollectionProjection;

import java.util.List;

public interface CollectionService {

    List<CollectionProjection> getTop5LargestCollections();
}
