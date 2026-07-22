package com.sp2603.project.repository.repository;

import com.sp2603.project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

}
