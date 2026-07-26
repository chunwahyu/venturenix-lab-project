package com.sp2603.project.mapper.cartItem;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.data.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemEntityMapper {

    @Mapping(target = "cid", ignore = true)
    @Mapping(target = "product", source = "productEntity")
    @Mapping(target = "user", source = "userEntity")
    CartItemEntity toCartItemEntity(UserEntity userEntity, ProductEntity productEntity, Integer quantity);
}
