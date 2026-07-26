package com.sp2603.project.service;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;

public interface TransactionProductService {

    TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity);
}
