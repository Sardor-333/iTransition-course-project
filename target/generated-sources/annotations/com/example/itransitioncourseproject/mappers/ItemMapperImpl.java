package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.Collection;
import com.example.itransitioncourseproject.entities.Item;
import com.example.itransitioncourseproject.payloads.request.item.ItemCreateDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-06T15:45:28+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl extends ItemMapper {

    @Override
    public Item mapFromCreateDtoToEntity(ItemCreateDto src, Collection collection) {
        if ( src == null && collection == null ) {
            return null;
        }

        Item item = new Item();

        if ( collection != null ) {
            item.setId( collection.getId() );
            item.setCreatedAt( collection.getCreatedAt() );
            item.setUpdatedAt( collection.getUpdatedAt() );
            item.setCreatedBy( collection.getCreatedBy() );
            item.setUpdatedBy( collection.getUpdatedBy() );
        }
        item.setName( src.getName() );
        item.setTags( getItemTags(src.getTagIdList()) );
        item.setCollection( collection );

        return item;
    }
}
