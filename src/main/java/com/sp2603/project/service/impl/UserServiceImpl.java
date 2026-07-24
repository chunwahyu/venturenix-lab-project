package com.sp2603.project.service.impl;

import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;
import com.sp2603.project.mapper.user.UserEntityMapper;
import com.sp2603.project.repository.UserRepository;
import com.sp2603.project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserEntity getUserEntityByEmail(FirebaseUserData firebaseUserData) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(firebaseUserData.getEmail());

        if(optionalUserEntity.isEmpty()) {
            UserEntity userEntity = userEntityMapper.toUserEntity(firebaseUserData);
            userRepository.save(userEntity);
            return userEntity;
        } else {
            return optionalUserEntity.get();
        }
    }
}
