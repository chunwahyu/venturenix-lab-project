package com.sp2603.project.data.cartItem.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GetUserCartResponseDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("cartQuantity")
    private Integer cartQuantity;

    @JsonProperty("stock")
    private Integer stock;
}
