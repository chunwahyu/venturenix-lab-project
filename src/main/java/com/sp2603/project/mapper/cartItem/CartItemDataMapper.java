package com.sp2603.project.mapper.cartItem;

import com.sp2603.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemDataMapper {
    CartItemResponseData toCartItemResponseData(CartItemEntity cartItemEntity);

    List<CartItemResponseData> toCartItemResponseDataList(List<CartItemEntity> cartItemEntityList);
}
