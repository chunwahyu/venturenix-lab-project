package com.sp2603.project.data.user.entity;

import com.sp2603.project.data.cartItem.entity.CartItemEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = false)
    private String firebaseUid;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<CartItemEntity> cartItemEntityList = new ArrayList<>();
}
