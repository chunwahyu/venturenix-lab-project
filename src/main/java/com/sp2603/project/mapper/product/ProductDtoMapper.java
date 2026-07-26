package com.sp2603.project.mapper.product;

import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.GetAllProductsResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.dto.response.CreateProductResponseDto;
import com.sp2603.project.data.product.dto.response.GetAllProductsResponseDto;
import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import com.sp2603.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {
    List<GetAllProductsResponseDto> toGetAllProductsResponseDtoList(List<GetAllProductsResponseData> getAllProductsResponseDataList);

    CreateProductResponseDto toCreateProductResponseDto(CreateProductResponseData createProductResponseData);

    ProductResponseDto toProductResponseDto(ProductResponseData productResponseData);

    ProductResponseDto toProductResponseDto(TransactionProductResponseData transactionProductResponseData);
}
