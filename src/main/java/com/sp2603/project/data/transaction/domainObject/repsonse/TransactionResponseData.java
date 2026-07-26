package com.sp2603.project.data.transaction.domainObject.repsonse;

import com.sp2603.project.data.transaction.status.TransactionStatus;
import com.sp2603.project.data.transactionProduct.domainObject.response.TransactionProductResponseData;
import com.sp2603.project.data.user.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponseData {
    private Integer tid;
    private UserEntity buyer;
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductResponseData> transactionProductResponseDataList = new ArrayList<>();
}
