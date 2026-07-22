package com.sp2603.project.data.product.domainObject.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseData {
    private Integer pid;
    private String name;
    private String imageUrl;
    private Double price;
    private Boolean hasStock;
}
