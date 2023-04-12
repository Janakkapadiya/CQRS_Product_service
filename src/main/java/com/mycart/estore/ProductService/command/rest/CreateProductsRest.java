package com.mycart.estore.ProductService.command.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class CreateProductsRest {
//    @NotBlank(message = "title required")
    private String title;
    @Min(value = 1,message = "price can not be lower then 1")
    private BigDecimal price;
    @Min(value = 1,message = "quantity can not be lower then 1")
    private Integer quantity;
}
