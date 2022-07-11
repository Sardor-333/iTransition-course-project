package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Field;
import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.enums.FieldType;
import com.example.itransitioncourseproject.exceptions.ObjectNotFoundException;
import com.example.itransitioncourseproject.payloads.request.ValueDto;
import com.example.itransitioncourseproject.repositories.FieldRepo;
import com.example.itransitioncourseproject.validation.DateTimeValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mapper(componentModel = "spring")
public abstract class ValueMapper {

    @Autowired
    private FieldRepo fieldRepo;

    @Autowired
    private DateTimeValidator dateTimeValidator;

    @Mapping(expression = "java(getField(src.getFieldName(), collectionId))", target = "field")
    @Mapping(expression = "java(getTargetValue(getField(src.getFieldName(), collectionId), src.getFieldValue()))", target = "targetValue")
    public abstract Value mapFromCreateDtoToEntity(ValueDto src, Long collectionId);

    @Named("getField")
    protected Field getField(String fieldName, Long collectionId) {
        return fieldRepo
                .findByCollectionIdAndName(collectionId, fieldName)
                .orElseThrow(() -> new ObjectNotFoundException("Collection does not have field with name: " + fieldName));
    }

    @Named("getTargetValue")
    protected String getTargetValue(Field field, String targetValue) {
        return validateTargetValue(field.getFieldType(), targetValue);
    }

    private String validateTargetValue(FieldType fieldType, String targetValue) {
        switch (fieldType) {

            case text:
            case textarea:
            case file:
                return targetValue;

            case number:
                if (!isNumeric(targetValue))
                    throw new IllegalStateException("Invalid numeric value!");
                return targetValue;

            case checkbox:
                if (!isBoolean(targetValue))
                    throw new IllegalStateException("Invalid boolean value!");
                return targetValue;

            case date:
                if (!dateTimeValidator.isValidDate(targetValue) && !dateTimeValidator.isValidDateTime(targetValue))
                    throw new IllegalStateException("Invalid date value!");
                return targetValue;

        }

        throw new IllegalStateException();
    }

    private boolean isNumeric(String strValue) {
        return matchesNumericPattern(strValue);
    }

    private boolean isBoolean(String source) {
        if (Objects.isNull(source))
            return false;

        return source.equalsIgnoreCase("true") ||
                source.equalsIgnoreCase("false") ||
                source.equalsIgnoreCase("on") ||
                source.equalsIgnoreCase("off");
    }

    private boolean matchesNumericPattern(String targetValue) {
        Pattern pattern = Pattern.compile("[0-9]+.[0-9]+");
        Matcher matcher = pattern.matcher(targetValue);
        return matcher.matches();
    }
}
