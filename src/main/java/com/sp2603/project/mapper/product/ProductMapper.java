package com.sp2603.project.mapper.product;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.domainObject.response.CreateProductResponseData;
import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.product.dto.request.CreateProductRequestDto;
import com.sp2603.project.data.product.dto.response.CreateProductResponseDto;
import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import com.sp2603.project.data.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "hasStock", expression = "java(productEntity.getStock() != null && productEntity.getStock() > 0)")
    ProductResponseData toProductResponseData(ProductEntity productEntity);
    List<ProductResponseData> toProductResponseDataList(Iterable<ProductEntity> productEntityLst);
    List<ProductResponseDto> toProductResponseDtoList(List<ProductResponseData> productResponseDataList);

    CreateProductRequestData toCreateProductRequestData(CreateProductRequestDto createProductRequestDto);

    @Mapping(target = "pid", ignore = true)
    ProductEntity toProductEntity(CreateProductRequestData createProductRequestData);
    CreateProductResponseData toCreateProductResponseData(ProductEntity productEntity);
    CreateProductResponseDto toCreateProductResponseDto(CreateProductResponseData createProductResponseData);
}
