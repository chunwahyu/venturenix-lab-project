package com.sp2603.project.service;

import com.sp2603.project.data.transaction.domainObject.repsonse.TransactionResponseData;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import jakarta.transaction.Transactional;

public interface TransactionService {
    @Transactional
    TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData);

    @Transactional
    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    void processTransaction(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    TransactionResponseData successTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);
}
