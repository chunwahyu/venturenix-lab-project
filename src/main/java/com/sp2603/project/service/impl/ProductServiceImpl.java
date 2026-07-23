package com.sp2603.project.service.impl;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.entity.ProductEntity;
import com.sp2603.project.exception.product.ProductNotFoundException;
import com.sp2603.project.mapper.product.ProductMapper;
import com.sp2603.project.repository.repository.ProductRepository;
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
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<GetAllProductsResponseData> getAllProducts() {
        Iterable<ProductEntity> productEntityList = productRepository.findAll();
        return productMapper.toGetAllProductsResponseDataList(productEntityList);
    }

    @Override
    public CreateProductResponseData createProduct(CreateProductRequestData createProductRequestData) {
        ProductEntity productEntity = productMapper.toProductEntity(createProductRequestData);
        productRepository.save(productEntity);
        return productMapper.toCreateProductResponseData(productEntity);
    }

    @Override
    public ProductResponseData getProductByPid(Integer pid) {
        try {
            ProductEntity productEntity = getEntityByPid(pid);

            return productMapper.toProductResponseData(productEntity);

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
