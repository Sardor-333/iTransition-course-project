package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.enums.UserRole;
import com.example.itransitioncourseproject.mappers.ItemMapper;
import com.example.itransitioncourseproject.mappers.ValueMapper;
import com.example.itransitioncourseproject.payloads.request.ValueCreateDto;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.FieldProjection;
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
    public List<FieldProjection> getCollectionFields(Long collectionId) {
        Optional<Collection> optional = collectionRepo.findById(collectionId);
        if (optional.isPresent()) {
            return fieldRepo.getCollectionFields(collectionId);
        }
        return null;
    }

    @Override
    public ApiResponse createItem(Long collectionId, ItemCreateDto itemCreateDto, User currentUser) {
        // todo : Get rid of duplicate code
        Collection collection = collectionRepo.findById(collectionId).orElse(null);

        if (collection == null) {
            return new ApiResponse(false, messageSource.getMessage("error.objectNotFound", new Object[]{"Collection", collectionId}, Locale.getDefault()));
        }

        if (AuthUtils.hasRole(currentUser, UserRole.ROLE_USER) && !collection.getUser().getId().equals(currentUser.getId())) {
            return new ApiResponse(false, messageSource.getMessage("error.accessDenied", null, Locale.getDefault()));
        }

        Item item = itemRepo.save(itemMapper.mapFromCreateDtoToEntity(itemCreateDto, collection));
        saveValues(itemCreateDto.getValueCreateDtoList(), item);

        return new ApiResponse(true, null);
    }

    private void saveValues(List<ValueCreateDto> valueCreateDtoList, Item item) {
        valueCreateDtoList
                .forEach(valueCreateDto -> {
                    Value value = valueMapper.mapFromCreateDtoToEntity(valueCreateDto, item);
                    valueRepo.save(value);
                });
    }
}
