package com.sp2603.project.service;

import com.sp2603.project.data.user.domainObject.request.FirebaseUserData;
import com.sp2603.project.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByEmail(FirebaseUserData firebaseUserData);
}
