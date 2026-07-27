package com.sp2603.project.exception;

import com.sp2603.project.data.error.dto.repsonse.ErrorResponseDto;
import com.sp2603.project.exception.cartItem.CartItemNotFoundException;
import com.sp2603.project.exception.cartItem.EmptyCartException;
import com.sp2603.project.exception.cartItem.InvalidQuantityException;
import com.sp2603.project.exception.cartItem.QuantityLimitExceededException;
import com.sp2603.project.exception.product.InsufficientStockException;
import com.sp2603.project.exception.product.ProductNotFoundException;
import com.sp2603.project.exception.transaction.InvalidTransactionStatusException;
import com.sp2603.project.exception.transaction.TransactionNotFoundException;
import com.sp2603.project.exception.transaction.TransactionNotMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(ProductNotFoundException exception) {
        log.warn("Get Product Failed: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "PRODUCT_NOT_FOUND",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDto> handleInsufficientStockException(InsufficientStockException exception) {
        log.warn("Insufficient Stock: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "INSUFFICIENT_STOCK",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyCartException(EmptyCartException exception) {
        log.warn("Empty Cart: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "EMPTY_CART",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidQuantityException(InvalidQuantityException exception) {
        log.warn("Invalid quantity: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_QUANTITY",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCartItemNotFoundException(CartItemNotFoundException exception) {
        log.warn("Cart Item Not Found: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "CART_ITEM_NOT_FOUND",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuantityLimitExceededException.class)
    public ResponseEntity<ErrorResponseDto> handleQuantityLimitExceededException(QuantityLimitExceededException exception) {
        log.warn("Exceeded quantity limit: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "QUANTITY_LIMIT_EXCEEDED",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidTransactionStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidTransactionStatusException(InvalidTransactionStatusException exception) {
        log.warn("Invalid Transaction Status: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                "INVALID_TRANSACTION_STATUS",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTransactionNotFoundException(TransactionNotFoundException exception) {
        log.warn("Cannot Find Transaction: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "TRANSACTION_NOT_FOUND",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotMatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTransactionNotMatchException(TransactionNotMatchException exception) {
        log.warn("No Transaction Matched: {}", exception.getMessage());

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "TRANSACTION_NOT_MATCH",
                exception.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
