package com.sp2603.project.data.transactionProduct.entity;

import com.sp2603.project.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
@Getter
@Setter
@NoArgsConstructor
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer quantity;
}
