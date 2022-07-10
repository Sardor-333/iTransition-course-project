package com.example.itransitioncourseproject.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

// todo : read about 'final' classes
public final class PageSizeUtils {

    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_SIZE = "25";

    private static final Integer MAX_SIZE = 20;
    private static final Integer MIN_SIZE = 5;

    // todo : reimplement logic
    public static <T, ID extends Serializable> void validatePageAndSize(Integer page, Integer size, JpaRepository<T, ID> repository) {
        // VALIDATING SIZE
        if (size < PageSizeUtils.MIN_SIZE)
            size = PageSizeUtils.MIN_SIZE;

        if (size > PageSizeUtils.MAX_SIZE)
            size = PageSizeUtils.MAX_SIZE;

        // VALIDATING PAGE
        if (page < Integer.parseInt(PageSizeUtils.DEFAULT_PAGE))
            page = Integer.parseInt(PageSizeUtils.DEFAULT_PAGE);

        long elementsCount = repository.count();
        int pagesCount = Integer.parseInt(String.valueOf(elementsCount % size == 0 ? elementsCount / size : elementsCount / size + 1));

        if (page > pagesCount)
            page = pagesCount;
    }
}
