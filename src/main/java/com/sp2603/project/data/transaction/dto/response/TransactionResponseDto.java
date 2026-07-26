package com.sp2603.project.data.transaction.dto.response;

import com.sp2603.project.data.transactionProduct.dto.response.TransactionItemResponseDto;
import com.sp2603.project.data.transactionProduct.entity.TransactionProductEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponseDto {
    private Integer tid;
    private Integer buyerUid;
    private LocalDateTime dateTime;
    private String status;
    private BigDecimal total;
    private List<TransactionItemResponseDto> items;
}
