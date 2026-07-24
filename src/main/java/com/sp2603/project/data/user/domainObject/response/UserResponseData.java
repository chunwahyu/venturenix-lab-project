package com.sp2603.project.data.user.domainObject.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseData {
    private Integer uid;
    private String email;
    private String firebaseUid;
}
