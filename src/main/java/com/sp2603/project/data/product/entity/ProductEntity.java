package com.sp2603.project.data.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;

@Entity
@Table(
        name = "product",
        indexes = {
                // Single-column index for fast product name searches/filtering
                @Index(name = "index_product_name", columnList = "name"),

                // Single-column index for sorting/filtering by price
                @Index(name = "index_product_price", columnList = "price"),

                // Composite index if you frequently query in-stock items sorted by price
                @Index(name = "index_product_stock_price", columnList = "stock, price")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Nullable Integer pid;

    @Column(nullable = false)
    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;
}
