package com.example.itransitioncourseproject.utils;

public class Validator {

    public static void validatePageAndSize(Integer page, Integer size) {
        if (size < 1 || size > Integer.parseInt(AppConstants.MAX_SIZE)) {
            size = Integer.parseInt(AppConstants.DEFAULT_SIZE);
        }
        if (page < Integer.parseInt(AppConstants.DEFAULT_PAGE)) page = 0;
    }
}
