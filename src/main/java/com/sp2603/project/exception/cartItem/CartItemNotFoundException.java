package com.sp2603.project.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(Integer pid) {
        super("Cart Item Not Found: " + pid);
    }
}
