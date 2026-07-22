package com.sp2603.project.service.impl;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.mapper.product.ProductMapper;
import com.sp2603.project.repository.repository.ProductRepository;
import com.sp2603.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponseData> getAllProducts() {
        Iterable<ProductEntity> productEntityList = productRepository.findAll();
        return productMapper.toProductResponseDataList(productEntityList);
    }

    @Override
    public CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData) {
        ProductEntity productEntity = productMapper.toProductEntity(createProductRequestData);
        productRepository.save(productEntity);
        return productMapper.toCreateProductResponseData(productEntity);
    }
}
