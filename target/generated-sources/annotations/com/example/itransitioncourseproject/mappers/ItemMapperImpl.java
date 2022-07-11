package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.payloads.request.item.ItemDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T15:49:51+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl extends ItemMapper {

    @Override
    public Item mapFromCreateDtoToEntity(ItemDto src) {
        if ( src == null ) {
            return null;
        }

        Item item = new Item();

        item.setName( src.getName() );
        item.setTags( getItemTags(src.getTagIdList()) );

        return item;
    }
}
