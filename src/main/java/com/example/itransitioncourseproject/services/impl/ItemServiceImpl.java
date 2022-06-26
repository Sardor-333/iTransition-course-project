package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.projections.ItemProjection;
import com.example.itransitioncourseproject.repositories.ItemRepo;
import com.example.itransitioncourseproject.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Override
    public List<ItemProjection> get5LatestAddedItems() {
        return itemRepo.get5LatestAddedItems();
    }
}
