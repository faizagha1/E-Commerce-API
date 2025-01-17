package com.E_Commerce.API.Cart;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private Integer quantity;
}
