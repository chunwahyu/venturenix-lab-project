package com.sp2603.project.controller;


import com.sp2603.project.data.transaction.domainObject.repsonse.TransactionResponseData;
import com.sp2603.project.data.transaction.dto.response.TransactionResponseDto;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.mapper.transaction.TransactionDtoMapper;
import com.sp2603.project.mapper.user.UserDataMapper;
import com.sp2603.project.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final UserDataMapper userDataMapper;
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;

    public TransactionController(UserDataMapper userDataMapper, TransactionService transactionService, TransactionDtoMapper transactionDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto prepareTransaction(@AuthenticationPrincipal Jwt jwt) {
        TransactionResponseData transactionResponseData = transactionService.prepareTransaction(userDataMapper.toFirebaseUserData(jwt));

        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
    }

    @GetMapping("/{tid}")
    public TransactionResponseDto getTransactionByTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        TransactionResponseData transactionResponseData = transactionService.getTransactionByTid(userDataMapper.toFirebaseUserData(jwt), tid);

        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
    }

    @PatchMapping("/{tid}/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processTransactionByTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        transactionService.processTransaction(userDataMapper.toFirebaseUserData(jwt), tid);
    }

    @PatchMapping("/{tid}/success")
    public TransactionResponseDto successTransactionByTid(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer tid) {
        TransactionResponseData transactionResponseData = transactionService.successTransactionByTid(userDataMapper.toFirebaseUserData(jwt), tid);

        return transactionDtoMapper.toTransactionResponseDto(transactionResponseData);
    }
}
