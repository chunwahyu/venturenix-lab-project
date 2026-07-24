package com.sp2603.project.service.impl;

import com.sp2603.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import com.sp2603.project.exception.cartItem.CartItemNotFoundException;
import com.sp2603.project.exception.cartItem.InvalidQuantityException;
import com.sp2603.project.exception.cartItem.QuantityLimitExceededException;
import com.sp2603.project.mapper.cartItem.CartItemDataMapper;
import com.sp2603.project.mapper.cartItem.CartItemEntityMapper;
import com.sp2603.project.repository.CartItemRepository;
import com.sp2603.project.service.CartItemService;
import com.sp2603.project.service.ProductService;
import com.sp2603.project.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final ProductService productService;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemDataMapper cartItemDataMapper;
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemEntityMapper cartItemEntityMapper, CartItemDataMapper cartItemDataMapper, CartItemRepository cartItemRepository) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemEntityMapper = cartItemEntityMapper;
        this.cartItemDataMapper = cartItemDataMapper;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);

        if(quantity <= 0) {
            throw new InvalidQuantityException();
        }

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, userEntity.getUid());

        if(optionalCartItemEntity.isPresent()) {
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();

            if(cartItemEntity.getQuantity() + quantity > cartItemEntity.getProduct().getStock()) {
                throw new QuantityLimitExceededException(cartItemEntity.getQuantity() + quantity);
            }

            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        } else {
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if(quantity > productEntity.getStock()) {
                throw new QuantityLimitExceededException(quantity);
            }
            CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(userEntity, productEntity, quantity);
            cartItemRepository.save(cartItemEntity);
        }
    }

    @Override
    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData) {
        return cartItemDataMapper.toCartItemResponseDataList(
                cartItemRepository.findAllByUser(
                        userService.getUserEntityByEmail(firebaseUserData)
                )
        );
    }

    @Transactional
    @Override
    public void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);

        if(quantity <= 0) {
            throw new InvalidQuantityException();
        }

        CartItemEntity cartItemEntity = getEntityByUidAndPid(userEntity.getUid(), pid);

        if(quantity > cartItemEntity.getProduct().getStock()) {
            throw new QuantityLimitExceededException(quantity);
        }

        cartItemEntity.setQuantity(quantity);

    }

    @Override
    @Transactional
    public void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid) {
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserData);

        int count = cartItemRepository.deleteByUserAndProduct_Pid(userEntity, pid);

        if(count <= 0) {
            throw new CartItemNotFoundException(pid);
        }
    }

    public CartItemEntity getEntityByUidAndPid(Integer uid, Integer pid) {
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, uid);

        if(optionalCartItemEntity.isEmpty()) {
            throw new CartItemNotFoundException(pid);
        }

        return optionalCartItemEntity.get();
    }
}
