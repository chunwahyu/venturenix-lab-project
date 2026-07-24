package com.sp2603.project.mapper.cartItem;

import com.sp2603.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.sp2603.project.data.cartItem.dto.response.CartItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemDtoMapper {

    @Mapping(target = "pid", source = "product.pid" )
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "stock", source = "product.stock")
    @Mapping(target = "cartQuantity", source = "quantity")
    CartItemResponseDto toCartItemResponseDto(CartItemResponseData cartItemResponseData);

    List<CartItemResponseDto> toCartItemResponseDtoList(List<CartItemResponseData> cartItemResponseDataList);
}
