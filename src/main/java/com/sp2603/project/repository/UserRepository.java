package com.sp2603.project.repository;

import com.sp2603.project.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM user WHERE email=?1"
    )
    Optional<UserEntity> findByEmail(String email);
}
