package com.sp2603.project.controller;

import com.sp2603.project.data.cartItem.dto.response.CartItemResponseDto;
import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import com.sp2603.project.exception.cartItem.CartItemNotFoundException;
import com.sp2603.project.mapper.cartItem.CartItemDtoMapper;
import com.sp2603.project.mapper.user.UserDataMapper;
import com.sp2603.project.repository.CartItemRepository;
import com.sp2603.project.service.CartItemService;
import com.sp2603.project.service.UserService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart/items")
public class CartItemController {
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper, CartItemRepository cartItemRepository, UserService userService) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable @NotNull @PositiveOrZero Integer pid, @PathVariable @NotNull @PositiveOrZero Integer quantity) {
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
