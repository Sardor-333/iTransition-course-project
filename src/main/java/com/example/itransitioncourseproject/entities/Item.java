package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "items")
@Indexed
public class Item extends AbsEntity {

    @FullTextField
    @Column(nullable = false)
    String name; // searchable field

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false, referencedColumnName = "id")
    Collection collection; // searchable field

    @IndexedEmbedded
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Value> values; // searchable field

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Like> likes;

    @IndexedEmbedded
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Comment> comments; // searchable field

    @IndexedEmbedded
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false, referencedColumnName = "id")
    )
    List<Tag> tags; // searchable field
}
