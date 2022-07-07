package com.example.itransitioncourseproject.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface ItemDetailProjection {

    Long getId();

    String getCreatedAt();

    String getUpdatedAt();

    String getName();

    // todo : delete if using thymeleaf it is possible to get model list length
    Integer getCommentsCount();

    Integer getLikesCount();

    @Value("#{@collectionRepo.getCollectionByItemId(target.id)}")
    CollectionProjection getCollection();

    @Value("#{@valueRepo.getValuesByItemId(target.id)}")
    List<ValueProjection> getValues();

    @Value("#{@likeRepo.getLikesByItemId(target.id)}")
    List<LikeProjection> getLikes();

    @Value("#{@commentRepo.getCommentsByItemId(target.id)}")
    List<CommentProjection> getComments();

    @Value("#{@tagRepo.getGetTagsByItemId(target.id)}")
    List<TagProjection> getTags();
}
