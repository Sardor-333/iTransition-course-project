package com.example.itransitioncourseproject.services.impl;

import com.example.itransitioncourseproject.entities.Tag;
import com.example.itransitioncourseproject.pagination.Paged;
import com.example.itransitioncourseproject.pagination.Paging;
import com.example.itransitioncourseproject.payloads.request.tag.TagCreateDto;
import com.example.itransitioncourseproject.payloads.response.ApiResponse;
import com.example.itransitioncourseproject.projections.TagProjection;
import com.example.itransitioncourseproject.repositories.ItemRepo;
import com.example.itransitioncourseproject.repositories.TagRepo;
import com.example.itransitioncourseproject.services.TagService;
import com.example.itransitioncourseproject.utils.PageSizeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    private final ItemRepo itemRepo;

    private final ResourceBundleMessageSource messageSource;

    @Override
    public List<TagProjection> getAllTags() {
        return tagRepo.getAllTags();
    }

    @Override
    public Paged<TagProjection> getTagsPageable(Integer page, Integer size) {
        PageSizeUtils.validatePageAndSize(page, size, tagRepo);
        Page<TagProjection> tagPage = tagRepo.getAllTagsPageable(PageRequest.of(page - 1, size));
        return new Paged<>(tagPage, Paging.of(tagPage.getTotalPages(), page, size));
    }

    @Override
    public ApiResponse createTag(TagCreateDto tagCreateDto) {
        if (tagRepo.existsByName(tagCreateDto.getName()))
            return new ApiResponse(false, messageSource.getMessage("error.tagAlreadyExists", new Object[]{tagCreateDto.getName()}, Locale.getDefault()));

        tagRepo.save(new Tag(tagCreateDto.getName()));
        return new ApiResponse(true, messageSource.getMessage("ok.tagCreated", null, Locale.getDefault()));
    }
}
