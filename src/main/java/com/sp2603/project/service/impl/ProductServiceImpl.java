package com.sp2603.project.service.impl;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.exception.product.ProductNotFoundException;
import com.sp2603.project.mapper.product.ProductDataMapper;
import com.sp2603.project.mapper.product.ProductEntityMapper;
import com.sp2603.project.repository.ProductRepository;
import com.sp2603.project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;
    private final ProductEntityMapper productEntityMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDataMapper productDataMapper, ProductEntityMapper productEntityMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
        this.productEntityMapper = productEntityMapper;
    }

    @Override
    public List<GetAllProductsResponseData> getAllProducts() {
        Iterable<ProductEntity> productEntityList = productRepository.findAll();
        return productDataMapper.toGetAllProductsResponseDataList(productEntityList);
    }

    @Override
    public CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData) {
        ProductEntity productEntity = productEntityMapper.toProductEntity(createProductRequestData);
        productRepository.save(productEntity);
        return productDataMapper.toCreateProductResponseData(productEntity);
    }

    @Override
    public ProductResponseData getProductByPid(Integer pid) {
        try {
            ProductEntity productEntity = getEntityByPid(pid);

            return productDataMapper.toProductResponseData(productEntity);

        } catch (Exception exception) {
            log.warn("Get Product Failed: {}", exception.getMessage());
            throw exception;
        }
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findByPid(pid);

        if(optionalProductEntity.isEmpty()) {
            throw new ProductNotFoundException(pid);
        }

        return optionalProductEntity.get();
    }
}