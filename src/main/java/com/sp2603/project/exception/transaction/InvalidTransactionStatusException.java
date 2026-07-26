package com.sp2603.project.exception.transaction;

import com.sp2603.project.data.transaction.status.TransactionStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionStatusException extends RuntimeException{
    public InvalidTransactionStatusException(TransactionStatus transactionStatus) {
        super("Invalid Transaction Status: " + transactionStatus);
    }
}
