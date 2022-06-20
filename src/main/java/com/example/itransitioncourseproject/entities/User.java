package com.example.itransitioncourseproject.entities;

import com.example.itransitioncourseproject.entities.abs.AbsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User extends AbsEntity implements UserDetails {

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    boolean isAccountNonExpired = true;

    @Column(nullable = false)
    boolean isAccountNonLocked = true;

    @Column(nullable = false)
    boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    boolean enabled = true;

    protected LocalDateTime loggedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    CloudinaryResource photo;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
    )
    List<Role> roles;

    @OneToMany(mappedBy = "owner")
    List<com.example.itransitioncourseproject.entities.Collection> collections;

    @OneToMany(mappedBy = "likedBy")
    List<Like> likes;

    @OneToMany(mappedBy = "user")
    List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String firstName, String lastName, String username, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
