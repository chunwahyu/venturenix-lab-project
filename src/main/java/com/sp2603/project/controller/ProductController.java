package com.sp2603.project.controller;

import com.sp2603.project.data.product.dto.request.CreateProductRequestDto;
import com.sp2603.project.data.product.dto.response.CreateProductResponseDto;
import com.sp2603.project.data.product.dto.response.GetAllProductsResponseDto;
import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import com.sp2603.project.mapper.product.ProductMapper;
import com.sp2603.project.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
    public List<GetAllProductsResponseDto> getAllProducts() {
        return productMapper.toGetAllProductsResponseDtoList(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductByPid(@PathVariable(value = "id") @NotBlank String pid) {
        return productMapper.toProductResponseDto(
                productService.getProductByPid(pid)
        );
    }
}
