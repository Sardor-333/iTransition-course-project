package com.example.itransitioncourseproject.utils;

public abstract class PageSizeUtils {

    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_SIZE = "5";
    public static final String MAX_SIZE = "20";

    public static void validatePageAndSize(Integer page, Integer size) {
        if (size < 1 || size > Integer.parseInt(PageSizeUtils.MAX_SIZE)) {
            size = Integer.parseInt(PageSizeUtils.DEFAULT_SIZE);
        }
        if (page < Integer.parseInt(PageSizeUtils.DEFAULT_PAGE)) page = 0;
    }
}
