package com.sp2603.project.mapper.transactionProduct;

import com.sp2603.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionProductDataMapper {
    TransactionProductResponseData toTransactionProductResponseData(TransactionProductEntity transactionProductEntity);

    List<TransactionProductResponseData> toTransactionProductResponseDataList(List<TransactionProductEntity> transactionProductEntityList);
}
