package com.mycart.estore.ProductService.query.rest;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class QueryProductRest {
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
