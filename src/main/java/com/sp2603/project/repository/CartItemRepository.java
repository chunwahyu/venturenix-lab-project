package com.sp2603.project.repository;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    Optional<CartItemEntity> findByProduct_PidAndUser_Uid(Integer pid, Integer uid);

    List<CartItemEntity> findAllByUser(UserEntity userEntity);

    int deleteByUserAndProduct_Pid(UserEntity userEntity, Integer pid);
}
