package com.sp2603.project.mapper.transaction;

import com.sp2603.project.data.transaction.domainObject.repsonse.TransactionResponseData;
import com.sp2603.project.data.transaction.dto.response.TransactionResponseDto;
import com.sp2603.project.mapper.transactionProduct.TransactionProductDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {TransactionProductDtoMapper.class}
)
public interface TransactionDtoMapper {

    @Mapping(target = "buyerUid", source = "transactionResponseData.buyer.uid")
    @Mapping(target = "items", source = "transactionResponseData.transactionProductResponseDataList")
    TransactionResponseDto toTransactionResponseDto(TransactionResponseData transactionResponseData);
}
