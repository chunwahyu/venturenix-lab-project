package com.sp2603.project.controller;

import com.sp2603.project.data.product.dto.request.CreateProductRequestDto;
import com.sp2603.project.data.product.dto.response.CreateProductResponseDto;
import com.sp2603.project.data.product.dto.response.GetAllProductsResponseDto;
import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import com.sp2603.project.mapper.product.ProductDataMapper;
import com.sp2603.project.mapper.product.ProductDtoMapper;
import com.sp2603.project.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/public/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;
    private final ProductDataMapper productDataMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper, ProductDataMapper productDataMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
        this.productDataMapper = productDataMapper;
    }

    @PostMapping
    public CreateProductResponseDto createProduct(@Valid @RequestBody CreateProductRequestDto createProductRequestDto) {
        return productDtoMapper.toCreateProductResponseDto(
                productService.createProduct(
                        productDataMapper.toCreateProductRequestData(createProductRequestDto)
                )
        );
    }

    @GetMapping
    public List<GetAllProductsResponseDto> getAllProducts() {
        return productDtoMapper.toGetAllProductsResponseDtoList(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductByPid(@PathVariable(value = "id") @NotNull @Positive Integer pid) {
        return productDtoMapper.toProductResponseDto(
                productService.getProductByPid(pid)
        );
    }
}
