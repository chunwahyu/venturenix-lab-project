package com.sp2603.project.mapper.transactionProduct;

import com.sp2603.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.sp2603.project.data.transactionProduct.dto.response.TransactionItemResponseDto;
import com.sp2603.project.mapper.product.ProductDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring",
        imports = {BigDecimal.class},
        uses = {ProductDtoMapper.class}
)
public interface TransactionProductDtoMapper {

    @Mapping(target = "product", source = "transactionProductResponseData")
    @Mapping(target = "subTotal",
            expression = "java(transactionProductResponseData.getPrice() != null ? transactionProductResponseData.getPrice().multiply(new BigDecimal(transactionProductResponseData.getQuantity())) : BigDecimal.ZERO)"
    )
    TransactionItemResponseDto toTransactionItemResponseDto(TransactionProductResponseData transactionProductResponseData);
}