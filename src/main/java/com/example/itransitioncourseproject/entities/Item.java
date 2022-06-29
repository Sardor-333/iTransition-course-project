package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "items")
public class Item extends AbsEntity {

    @Column(nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false, referencedColumnName = "id")
    Collection collection;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Value> values;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Like> likes;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    List<Comment> comments;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false, referencedColumnName = "id")
    )
    List<Tag> tags;
}
