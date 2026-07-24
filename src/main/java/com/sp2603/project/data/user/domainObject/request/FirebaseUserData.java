package com.sp2603.project.data.user.domainObject.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FirebaseUserData {
    private String email;
    private String firebaseUid;
}
