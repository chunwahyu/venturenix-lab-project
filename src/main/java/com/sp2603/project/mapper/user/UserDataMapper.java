package com.sp2603.project.mapper.user;

import com.sp2603.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.domainObject.response.UserResponseData;
import com.sp2603.project.data.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Mapping(target = "email", expression = "java(jwt.getClaimAsString(\"email\"))")
    @Mapping(target = "firebaseUid", expression = "java(jwt.getClaimAsString(\"user_id\"))")
    FirebaseUserData toFirebaseUserData(Jwt jwt);

    //UserResponseData toUserResponseData(UserEntity userEntity);

    //List<CartItemResponseData> toCartItemResponseDataList(List<UserEntity> userEntityList);
}
