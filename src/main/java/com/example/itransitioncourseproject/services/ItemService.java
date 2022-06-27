package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.ItemProjection;

import java.util.List;

public interface ItemService {

    List<ItemProjection> get5LatestAddedItems();

    List<ItemProjection> getItems();

    List<ItemProjection> getItemsByCollectionId(Long collectionId);

    List<ItemProjection> getItemsByTagId(Long tagId);
}
