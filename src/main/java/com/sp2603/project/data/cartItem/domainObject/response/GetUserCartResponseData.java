package com.sp2603.project.data.cartItem.domainObject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GetUserCartResponseData {
    private Integer pid;
    private String imageUrl;
    private BigDecimal price;
    private Integer cartQuantity;
    private Integer stock;
}
