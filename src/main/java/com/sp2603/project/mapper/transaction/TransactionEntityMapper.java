package com.sp2603.project.mapper.transaction;

import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.data.transaction.status.TransactionStatus;
import com.sp2603.project.data.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class, TransactionStatus.class, BigDecimal.class}
)
public interface TransactionEntityMapper {

    @Mapping(target = "tid", ignore = true)
    @Mapping(target = "buyer", source = "userEntity")
    @Mapping(target = "dateTime", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", constant = "PREPARE")
    @Mapping(target = "total", expression = "java(BigDecimal.ZERO)")
    @Mapping(target = "transactionProductEntityList", ignore = true)
    TransactionEntity toTransactionEntity(UserEntity userEntity);
}
