package com.sp2603.project.service.impl;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;
import com.sp2603.project.mapper.transactionProduct.TransactionProductEntityMapper;
import com.sp2603.project.repository.TransactionProductRepository;
import com.sp2603.project.service.TransactionProductService;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductEntityMapper transactionProductEntityMapper;
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductEntityMapper transactionProductEntityMapper, TransactionProductRepository transactionProductRepository) {
        this.transactionProductEntityMapper = transactionProductEntityMapper;
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity) {
        TransactionProductEntity transactionProductEntity = transactionProductEntityMapper.toTransactionProductEntity(transactionEntity, cartItemEntity);
        transactionProductRepository.save(transactionProductEntity);
        return transactionProductEntity;
    }
}
