package com.sp2603.project.mapper.transactionProduct;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionProductEntityMapper {
    @Mapping(target = "tpid", ignore = true)
    @Mapping(target = "transaction", source = "transactionEntity")
    @Mapping(target = "pid", source = "cartItemEntity.product.pid")
    @Mapping(target = "name", source = "cartItemEntity.product.name")
    @Mapping(target = "description", source = "cartItemEntity.product.description")
    @Mapping(target = "imageUrl", source = "cartItemEntity.product.imageUrl")
    @Mapping(target = "price", source = "cartItemEntity.product.price")
    @Mapping(target = "stock", source = "cartItemEntity.product.stock")
    TransactionProductEntity toTransactionProductEntity(TransactionEntity transactionEntity, CartItemEntity cartItemEntity);
}
