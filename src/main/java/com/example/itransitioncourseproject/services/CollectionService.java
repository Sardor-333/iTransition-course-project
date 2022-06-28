package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.CollectionDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CollectionService {

    List<CollectionProjection> getTop5LargestCollections();

    Paged<CollectionProjection> getCollections(Integer page, Integer size);

    Paged<CollectionProjection> getMyCollections(Integer page, Integer size, User currentUser);

    ApiResponse createCollection(CollectionDto collectionDto, MultipartFile photo, User currentUser);
}
