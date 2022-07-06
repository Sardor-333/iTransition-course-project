package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.entities.Value;
import com.example.itransitioncourseproject.payloads.request.ValueCreateDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-06T16:09:03+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ValueMapperImpl extends ValueMapper {

    @Override
    public Value mapFromCreateDtoToEntity(ValueCreateDto src, Item item) {
        if ( src == null && item == null ) {
            return null;
        }

        Value value = new Value();

        if ( item != null ) {
            value.setId( item.getId() );
            value.setCreatedAt( item.getCreatedAt() );
            value.setUpdatedAt( item.getUpdatedAt() );
            value.setCreatedBy( item.getCreatedBy() );
            value.setUpdatedBy( item.getUpdatedBy() );
        }
        value.setItem( item );
        value.setField( getField(src.getFieldName(), item) );
        value.setTargetValue( getTargetValue(getField(src.getFieldName(), item), src.getFieldValue()) );

        return value;
    }
}
