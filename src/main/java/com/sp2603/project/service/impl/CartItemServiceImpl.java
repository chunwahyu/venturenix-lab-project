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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);
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
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        if(quantity <= 0) {
            log.warn("Invalid Quantity: {}", quantity);
            throw new InvalidQuantityException(quantity);
        }

        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, userEntity.getUid());

        if(optionalCartItemEntity.isPresent()) {
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();

            if(cartItemEntity.getQuantity() + quantity > cartItemEntity.getProduct().getStock()) {
                log.warn("Total quantity limit exceeded: {}", cartItemEntity.getQuantity() + quantity);
                throw new QuantityLimitExceededException(cartItemEntity.getQuantity() + quantity);
            }

            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        } else {
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if(quantity > productEntity.getStock()) {
                log.warn("Quantity limit exceeded: {}", quantity);
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
                        userService.getUserEntityByFirebaseUserData(firebaseUserData)
                )
        );
    }

    @Override
    public List<CartItemEntity> getEntityListByUserEntity(UserEntity userEntity) {
        return cartItemRepository.findAllByUser(userEntity);
    }

    @Transactional
    @Override
    public void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        if(quantity <= 0) {
            log.warn("Update Failed, invalid quantity: {}", quantity);
            throw new InvalidQuantityException(quantity);
        }

        CartItemEntity cartItemEntity = getEntityByUidAndPid(userEntity.getUid(), pid);

        if(quantity > cartItemEntity.getProduct().getStock()) {
            log.warn("Update Failed, quantity exceeded: {}", quantity);
            throw new QuantityLimitExceededException(quantity);
        }

        cartItemEntity.setQuantity(quantity);

    }

    @Override
    @Transactional
    public void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid) {
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        int count = cartItemRepository.deleteByUserAndProduct_Pid(userEntity, pid);

        if(count <= 0) {
            log.warn("Delete Cart Item Failed: {}", pid);
            throw new CartItemNotFoundException(pid);
        }
    }

    public CartItemEntity getEntityByUidAndPid(Integer uid, Integer pid) {
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, uid);

        if(optionalCartItemEntity.isEmpty()) {
            log.warn("No Cart Item Found: {}", pid);
            throw new CartItemNotFoundException(pid);
        }

        return optionalCartItemEntity.get();
    }

    @Override
    public void emptyUserCart(UserEntity userEntity) {
        cartItemRepository.deleteAllByUser(userEntity);
    }
}
