package com.sp2603.project.data.cartItem.entity;

import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_item", indexes = {
        @Index(name = "index_cart_item_uid_pid", columnList = "uid, pid"),
})
@Getter
@Setter
@NoArgsConstructor
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;
}
