package com.sp2603.project.mapper.transaction;

import com.sp2603.project.data.transaction.domainObject.repsonse.TransactionResponseData;
import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.mapper.transactionProduct.TransactionProductDataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {TransactionProductDataMapper.class}
)
public interface TransactionDataMapper {

    @Mapping(target = "transactionProductResponseDataList", source = "transactionProductEntityList")
    TransactionResponseData toTransactionResponseData(TransactionEntity transactionEntity);

}
