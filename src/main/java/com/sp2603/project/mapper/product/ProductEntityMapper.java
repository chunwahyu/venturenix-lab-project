package com.sp2603.project.mapper.product;

import com.sp2603.project.data.product.domainObject.request.CreateProductRequestData;
import com.sp2603.project.data.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    @Mapping(target = "pid", ignore = true)
    ProductEntity toProductEntity(CreateProductRequestData createProductRequestData);
}
