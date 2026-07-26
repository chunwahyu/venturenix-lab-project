package com.sp2603.project.data.transactionProduct.dto.response;


import com.sp2603.project.data.product.dto.response.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TransactionItemResponseDto {
    private Integer tpid;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal subTotal;
}
