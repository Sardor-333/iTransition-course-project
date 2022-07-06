package com.example.itransitioncourseproject.validation.impl;

import com.example.itransitioncourseproject.validation.DateTimeValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public class DateTimeValidatorImpl implements DateTimeValidator {

    @Override
    public boolean isValidDate(String source) {
        if (Objects.isNull(source))
            return false;

        try {
            LocalDate.parse(source);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isValidDateTime(String source) {
        if (Objects.isNull(source))
            return false;

        try {
            LocalDateTime.parse(source);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
}
