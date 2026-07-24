package com.sp2603.project.mapper.user;

import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.jwt.Jwt;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    @Mapping(target = "uid", ignore = true)
    public UserEntity toUserEntity(FirebaseUserData firebaseUserData);
}
