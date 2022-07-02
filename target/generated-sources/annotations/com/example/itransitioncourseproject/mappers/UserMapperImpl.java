package com.example.itransitioncourseproject.mappers;

import com.example.itransitioncourseproject.entities.User;
import com.example.itransitioncourseproject.payloads.request.ProfileDto;
import com.example.itransitioncourseproject.payloads.request.RegisterDto;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-02T00:06:13+0500",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public void mapFromProfileDtoToUser(ProfileDto src, User target) {
        if ( src == null ) {
            return;
        }

        target.setFirstName( src.getFirstName() );
        target.setLastName( src.getLastName() );
        target.setUsername( src.getUsername() );
    }

    @Override
    public User mapFromRegisterDtoToUser(RegisterDto src) {
        if ( src == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( src.getFirstName() );
        user.setLastName( src.getLastName() );
        user.setUsername( src.getUsername() );

        user.setPassword( encodePassword(src.getPassword()) );

        return user;
    }
}
