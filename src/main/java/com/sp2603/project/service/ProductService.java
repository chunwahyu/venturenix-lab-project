package com.sp2603.project.service;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;

import java.util.List;

public interface ProductService {

    List<ProductResponseData> getAllProducts();

    CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData);
}
