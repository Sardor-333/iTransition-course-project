package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.mappers.ItemMapper;
import com.example.itransitioncourseproject.mappers.ValueMapper;
import com.example.itransitioncourseproject.payloads.request.SearchDto;
import com.example.itransitioncourseproject.payloads.request.ValueDto;
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
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private static final String[] SEARCHABLE_FIELDS = {
            "name",

            "collection.name",
            "collection.description",
            "collection.fields.name",
            "collection.topic.name",

            "values.targetValue",
            "comments.body",
            "tags.name"
    };

    private final ItemRepo itemRepo;

    private final CollectionRepo collectionRepo;

    private final FieldRepo fieldRepo;

    private final ResourceBundleMessageSource messageSource;

    private final ItemMapper itemMapper;

    private final ValueMapper valueMapper;

    private final ValueRepo valueRepo;

    private final AuthUtils authUtils;

    private final EntityManager entityManager;

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
    public ItemDetailProjection getItemDetailsById(Long itemId, User currentUser) {
        if (!itemRepo.existsById(itemId))
            throw new ObjectNotFoundException("Item with id: " + itemId + " not found!");
        return itemRepo.getItemDetailById(itemId, currentUser == null ? null : currentUser.getId());
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
        saveValuesOnCreate(itemCreateDto.getValueDtoList(), item);

        return new ApiResponse(true, null);
    }

    private void saveValuesOnCreate(List<ValueDto> valueDtoList, Item item) {
        valueDtoList
                .forEach(valueDto -> {
                    Value value = valueMapper.mapFromCreateDtoToEntity(valueDto, item.getCollection().getId());
                    value.setItem(item);
                    valueRepo.save(value);
                });
    }

    @Override
    public ApiResponse deleteItemById(Long itemId, User currentUser) {
        Item item = itemRepo
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("Item with id: " + itemId + " not found!"));

        boolean userHasAccessToCollection = authUtils.userHasAccessToCollection(item.getCollection(), currentUser);
        if (!userHasAccessToCollection)
            return new ApiResponse(false, messageSource.getMessage("error.accessDenied", null, Locale.getDefault()));

        itemRepo.delete(item);
        return new ApiResponse(true, messageSource.getMessage("ok.itemDeleted", null, Locale.getDefault()));
    }

    @Override
    public com.example.itransitioncourseproject.payloads.response.SearchResult<Item> searchItems(SearchDto searchDto) {
        SearchSession searchSession = Search.session(entityManager);

        SearchResult<Item> itemSearchResult = searchSession
                .search(Item.class)
                .where(f -> f.match()
                        .fields(SEARCHABLE_FIELDS)
                        .matching(searchDto.getSearchKey()))
                .fetch(Integer.MAX_VALUE);

        Duration duration = itemSearchResult.took();
        return new com.example.itransitioncourseproject.payloads.response.SearchResult<>(itemSearchResult.hits(), duration.toMillis());
    }
}
