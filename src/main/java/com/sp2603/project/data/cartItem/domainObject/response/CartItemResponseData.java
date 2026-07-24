package com.sp2603.project.data.cartItem.domainObject.response;

import com.sp2603.project.data.product.domainObject.response.ProductResponseData;
import com.sp2603.project.data.user.domainObject.response.UserResponseData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemResponseData {
    private Integer cid;
    private ProductResponseData product;
    private UserResponseData user;
    private Integer quantity;
}
