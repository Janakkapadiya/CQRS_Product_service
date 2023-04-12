package com.mycart.estore.ProductService.command.rest;

import com.mycart.estore.ProductService.command.CreateProductCommand;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public ProductsCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductsRest products) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(products.getPrice())
                .title(products.getTitle())
                .quantity(products.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        String returnValue;
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            returnValue = e.getLocalizedMessage();
        }
        return returnValue;
    }
}
