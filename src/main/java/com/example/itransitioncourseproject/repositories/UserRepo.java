package com.example.itransitioncourseproject.repositories;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.projections.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Long id); // when updating username

    @Query(
            nativeQuery = true,
            value = "select u.id                                                as id,\n" +
                    "       to_char(u.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(u.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       u.first_name                                        as firstName,\n" +
                    "       u.last_name                                         as lastName,\n" +
                    "       u.username                                          as username,\n" +
                    "       u.enabled                                           as enabled,\n" +
                    "       to_char(u.logged_at, 'yyyy-MM-dd HH24:MI')  as loggedAt,\n" +
                    "       cr.secure_url                                       as imgUrl\n" +
                    "from users u\n" +
                    "         left join cloudinary_resources cr on cr.id = u.photo_id"
    )
    Page<UserProjection> getUsers(Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "select u.id                                                as id,\n" +
                    "       to_char(u.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(u.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       u.first_name                                        as firstName,\n" +
                    "       u.last_name                                         as lastName,\n" +
                    "       u.username                                          as username,\n" +
                    "       u.enabled                                           as enabled,\n" +
                    "       to_char(u.logged_at, 'yyyy-MM-dd HH24:MI')  as loggedAt,\n" +
                    "       cr.secure_url                                       as imgUrl\n" +
                    "from users u\n" +
                    "         left join cloudinary_resources cr on cr.id = u.photo_id\n" +
                    "where u.id = :id"
    )
    UserProjection getUser(@Param("id") Long id);

    @Query(
            nativeQuery = true,
            value = "select u.id                                                as id,\n" +
                    "       to_char(u.created_at, 'yyyy-MM-dd HH24:MI') as createdAt,\n" +
                    "       to_char(u.updated_at, 'yyyy-MM-dd HH24:MI') as updatedAt,\n" +
                    "       u.first_name                                        as firstName,\n" +
                    "       u.last_name                                         as lastName,\n" +
                    "       u.username                                          as username,\n" +
                    "       u.enabled                                           as enabled,\n" +
                    "       to_char(u.logged_at, 'yyyy-MM-dd HH24:MI')  as loggedAt,\n" +
                    "       cr.secure_url                                       as imgUrl\n" +
                    "from users u\n" +
                    "         left join cloudinary_resources cr on cr.id = u.photo_id\n " +
                    "join collections c on u.id = c.user_id " +
                    "where c.id = :collectionId"
    )
    UserProjection getUserByCollectionId(@Param("collectionId") Long id);
}
