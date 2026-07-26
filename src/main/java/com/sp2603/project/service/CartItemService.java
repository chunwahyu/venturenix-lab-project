package com.sp2603.project.service;

import com.sp2603.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);

    List<CartItemEntity> getEntityListByUserEntity(UserEntity userEntity);

    @Transactional
    void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    void emptyUserCart(UserEntity userEntity);
}
