package com.sp2603.project.service;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.entity.ProductEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService {

    List<GetAllProductsResponseData> getAllProducts();

    CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData);

    ProductResponseData getProductByPid(Integer pid);

    ProductEntity getEntityByPid(Integer pid);

    @Transactional
    void reduceStock(Integer pid, Integer quantity);
}
