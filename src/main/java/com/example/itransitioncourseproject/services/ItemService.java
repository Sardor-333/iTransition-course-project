package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.FieldProjection;
import com.example.itransitioncourseproject.projections.ItemProjection;

import java.util.List;

public interface ItemService {

    List<ItemProjection> get5LatestAddedItems();

    List<ItemProjection> getItems();

    List<ItemProjection> getItemsByCollectionId(Long collectionId);

    List<ItemProjection> getItemsByTagId(Long tagId);

    List<FieldProjection> getCollectionFields(Long collectionId);

    ApiResponse createItem(Long collectionId, ItemCreateDto itemCreateDto, User currentUser);
}
