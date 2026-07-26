package com.sp2603.project.service.impl;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.transaction.domainObject.repsonse.TransactionResponseData;
import com.sp2603.project.data.transaction.entity.TransactionEntity;
import com.sp2603.project.data.transaction.status.TransactionStatus;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import com.sp2603.project.exception.cartItem.EmptyCartException;
import com.sp2603.project.exception.transaction.InvalidTransactionStatusException;
import com.sp2603.project.exception.transaction.TransactionNotFoundException;
import com.sp2603.project.exception.transaction.TransactionNotMatchException;
import com.sp2603.project.mapper.transaction.TransactionDataMapper;
import com.sp2603.project.mapper.transaction.TransactionEntityMapper;
import com.sp2603.project.repository.TransactionRepository;
import com.sp2603.project.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionEntityMapper transactionEntityMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    private final TransactionDataMapper transactionDataMapper;
    private final ProductService productService;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionEntityMapper transactionEntityMapper, TransactionRepository transactionRepository, TransactionProductService transactionProductService, TransactionDataMapper transactionDataMapper, ProductService productService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionEntityMapper = transactionEntityMapper;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
        this.transactionDataMapper = transactionDataMapper;
        this.productService = productService;
    }

    @Transactional
    @Override
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        List<CartItemEntity> cartItemEntityList = cartItemService.getEntityListByUserEntity(userEntity);

        if(cartItemEntityList.isEmpty()) {
            log.warn("Empty Cart: {}", userEntity.getUid());
            throw new EmptyCartException();
        }

        TransactionEntity transactionEntity = transactionEntityMapper.toTransactionEntity(userEntity);
        transactionEntity = transactionRepository.save(transactionEntity);

        for(CartItemEntity cartItemEntity: cartItemEntityList) {
            TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);
            transactionEntity.getTransactionProductEntityList().add(transactionProductEntity);
            transactionEntity.setTotal(
                    transactionEntity.getTotal().add(
                        transactionProductEntity.getPrice().multiply(new BigDecimal(transactionProductEntity.getQuantity()))
                    )
            );
        }

        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }

    @Transactional
    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        TransactionEntity transactionEntity = getEntityByTid(tid);

        validateTransactionUser(transactionEntity, userEntity);

        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }

    @Transactional
    @Override
    public void processTransaction(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        TransactionEntity transactionEntity = getEntityByTid(tid);

        validateTransactionUser(transactionEntity, userEntity);

        if(transactionEntity.getStatus() != TransactionStatus.PREPARE) {
            throw new InvalidTransactionStatusException(transactionEntity.getStatus());
        }

        transactionEntity.setStatus(TransactionStatus.PROCESSING);
    }

    @Transactional
    @Override
    public TransactionResponseData successTransactionByTid(FirebaseUserData firebaseUserData, Integer tid) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        TransactionEntity transactionEntity = getEntityByTid(tid);

        validateTransactionUser(transactionEntity, userEntity);

        if(transactionEntity.getStatus() != TransactionStatus.PROCESSING) {
            throw new InvalidTransactionStatusException(transactionEntity.getStatus());
        }

        for(TransactionProductEntity transactionProductEntity: transactionEntity.getTransactionProductEntityList()) {
            productService.reduceStock(transactionProductEntity.getPid(), transactionProductEntity.getQuantity());
        }

        cartItemService.emptyUserCart(userEntity);

        transactionEntity.setStatus(TransactionStatus.SUCCESS);

        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }


    public TransactionEntity getEntityByTid(Integer tid) {
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(tid);

        if(optionalTransactionEntity.isEmpty()) {
            throw new TransactionNotFoundException(tid);
        }

        return optionalTransactionEntity.get();
    }

    public void validateTransactionUser(TransactionEntity transactionEntity, UserEntity userEntity) {
        if(!transactionEntity.getBuyer().getUid().equals(userEntity.getUid())) {
            throw new TransactionNotMatchException(userEntity.getUid());
        }
    }
}
