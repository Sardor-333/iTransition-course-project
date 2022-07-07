package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.mappers.ItemMapper;
import com.example.itransitioncourseproject.mappers.ValueMapper;
import com.example.itransitioncourseproject.payloads.request.ValueCreateDto;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.FieldProjection;
import com.example.itransitioncourseproject.projections.ItemDetailProjection;
import com.example.itransitioncourseproject.projections.ItemProjection;
import com.example.itransitioncourseproject.repositories.CollectionRepo;
import com.example.itransitioncourseproject.repositories.FieldRepo;
import com.example.itransitioncourseproject.repositories.ItemRepo;
import com.example.itransitioncourseproject.repositories.ValueRepo;
import com.example.itransitioncourseproject.services.ItemService;
import com.example.itransitioncourseproject.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    private final CollectionRepo collectionRepo;

    private final FieldRepo fieldRepo;

    private final ResourceBundleMessageSource messageSource;

    private final ItemMapper itemMapper;

    private final ValueMapper valueMapper;

    private final ValueRepo valueRepo;

    private final AuthUtils authUtils;

    @Override
    public List<ItemProjection> get5LatestAddedItems() {
        return itemRepo.get5LatestAddedItems();
    }

    @Override
    public List<ItemProjection> getItems() {
        return itemRepo.getAllItems();
    }

    @Override
    public List<ItemProjection> getItemsByCollectionId(Long collectionId) {
        return itemRepo.getItemsByCollectionId(collectionId);
    }

    @Override
    public List<ItemProjection> getItemsByTagId(Long collectionId) {
        return itemRepo.getItemsByTagId(collectionId);
    }

    @Override
    public ItemDetailProjection getItemDetailsById(Long itemId) {
        if (!itemRepo.existsById(itemId))
            throw new ObjectNotFoundException("Item with id: " + itemId + " not found!");
        return itemRepo.getItemDetailById(itemId);
    }

    @Override
    public List<FieldProjection> getCollectionFields(Long collectionId) {
        Optional<Collection> optional = collectionRepo.findById(collectionId);
        if (optional.isPresent())
            return fieldRepo.getCollectionFields(collectionId);
        return null;
    }

    @Override
    public ApiResponse createItem(Long collectionId, ItemCreateDto itemCreateDto, User currentUser) {
        Collection collection = collectionRepo.findById(collectionId).orElse(null);
        boolean userHasAccessToCollection = authUtils.userHasAccessToCollection(collection, currentUser);
        if (!userHasAccessToCollection)
            return new ApiResponse(false, messageSource.getMessage("error.accessDenied", null, Locale.getDefault()));

        Item item = itemMapper.mapFromCreateDtoToEntity(itemCreateDto);
        item.setCollection(collection);
        itemRepo.save(item);
        saveValuesOnCreate(itemCreateDto.getValueCreateDtoList(), item);

        return new ApiResponse(true, null);
    }

    private void saveValuesOnCreate(List<ValueCreateDto> valueCreateDtoList, Item item) {
        valueCreateDtoList
                .forEach(valueCreateDto -> {
                    Value value = valueMapper.mapFromCreateDtoToEntity(valueCreateDto, item.getCollection().getId());
                    value.setItem(item);
                    valueRepo.save(value);
                });
    }
}
