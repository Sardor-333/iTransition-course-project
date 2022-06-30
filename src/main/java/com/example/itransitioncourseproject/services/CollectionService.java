package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.payloads.request.collection.CollectionCreateDto;
import com.example.itransitioncourseproject.payloads.request.collection.CollectionEditDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.CollectionProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CollectionService {

    // FOR HOME
    List<CollectionProjection> getTop5LargestCollections();

    // FOR ALL
    Paged<CollectionProjection> getCollections(Integer page, Integer size);

    // FOR AUTHENTICATED USER
    Paged<CollectionProjection> getMyCollections(Integer page, Integer size, User currentUser);

    CollectionProjection getCollectionById(Long collectionId);

    ApiResponse createCollection(CollectionCreateDto collectionCreateDto, MultipartFile photo, User currentUser);

    ApiResponse deleteCollection(Long collectionId, User currentUser);

    ApiResponse editCollection(Long collectionId, CollectionEditDto collectionEditDto, MultipartFile img, User currentUser);
}
