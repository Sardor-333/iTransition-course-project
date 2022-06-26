package com.example.itransitioncourseproject.services;

import com.example.itransitioncourseproject.projections.ItemProjection;

import java.util.List;

public interface ItemService {

    List<ItemProjection> get5LatestAddedItems();
}
