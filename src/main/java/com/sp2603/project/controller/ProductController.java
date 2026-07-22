package com.sp2603.project.controller;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.dto.request.CreateProductRequestDto;
import com.sp2603.project.data.product.dto.response.CreateProductResponseDto;
import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import com.sp2603.project.mapper.product.ProductMapper;
import com.sp2603.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/public/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public CreateProductResponseDto createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        return productMapper.toCreateProductResponseDto(
                productService.createProduct(
                        productMapper.toCreateProductRequestData(createProductRequestDto)
                )
        );
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.toProductResponseDtoList(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{id}")
    public void getProductByPid() {

    }
}
