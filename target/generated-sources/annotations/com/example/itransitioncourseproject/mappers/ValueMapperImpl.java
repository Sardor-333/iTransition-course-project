package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.payloads.request.ValueDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T15:49:51+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ValueMapperImpl extends ValueMapper {

    @Override
    public Value mapFromCreateDtoToEntity(ValueDto src, Long collectionId) {
        if ( src == null && collectionId == null ) {
            return null;
        }

        Value value = new Value();

        value.setField( getField(src.getFieldName(), collectionId) );
        value.setTargetValue( getTargetValue(getField(src.getFieldName(), collectionId), src.getTargetValue()) );

        return value;
    }
}
