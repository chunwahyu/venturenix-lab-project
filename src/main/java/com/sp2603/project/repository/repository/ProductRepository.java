package com.sp2603.project.repository.repository;

import com.sp2603.project.data.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM product WHERE pid=?1"
    )
    Optional<ProductEntity> findByPid(Integer pid);
}
