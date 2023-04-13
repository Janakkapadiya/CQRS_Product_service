package com.mycart.estore.ProductService.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage{
    private final Date timestamp;
    private final String message;
}
