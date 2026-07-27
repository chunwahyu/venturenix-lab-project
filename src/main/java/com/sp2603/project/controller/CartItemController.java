package com.sp2603.project.controller;

import com.sp2603.project.data.cartItem.dto.response.CartItemResponseDto;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.mapper.cartItem.CartItemDtoMapper;
import com.sp2603.project.mapper.user.UserDataMapper;
import com.sp2603.project.service.CartItemService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")
public class CartItemController {
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.putCartItem(firebaseUserData, pid, quantity);
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCart(@AuthenticationPrincipal Jwt jwt) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);

        return cartItemDtoMapper.toCartItemResponseDtoList(
                cartItemService.getUserCart(firebaseUserData)
        );
    }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.updateCartQuantity(firebaseUserData, pid, quantity);
    }

    @DeleteMapping("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.deleteCartItem(firebaseUserData, pid);
    }
}
