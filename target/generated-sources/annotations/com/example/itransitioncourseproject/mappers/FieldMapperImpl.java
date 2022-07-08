package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Field;
import com.example.itransitioncourseproject.payloads.request.FieldDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-08T14:07:35+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class FieldMapperImpl extends FieldMapper {

    @Override
    public Field mapFromCreateDtoToEntity(FieldDto src) {
        if ( src == null ) {
            return null;
        }

        Field field = new Field();

        field.setName( src.getName() );

        field.setFieldType( getFieldType(src.getType()) );

        return field;
    }
}
