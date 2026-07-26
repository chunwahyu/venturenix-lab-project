package com.sp2603.project.repository;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import com.sp2603.project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM cart_item WHERE pid=?1 AND uid=?2"
    )
    Optional<CartItemEntity> findByProduct_PidAndUser_Uid(Integer pid, Integer uid);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM cart_item WHERE uid = :#{#user.uid}"
    )
    List<CartItemEntity> findAllByUser(@Param("user") UserEntity userEntity);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE FROM cart_item WHERE uid = :#{#user.uid} AND pid = :pid"
    )
    int deleteByUserAndProduct_Pid(@Param("user") UserEntity userEntity, @Param("pid") Integer pid);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE FROM cart_item WHERE uid = :#{#user.uid}"
    )
    void deleteAllByUser(@Param("user") UserEntity userEntity);
}