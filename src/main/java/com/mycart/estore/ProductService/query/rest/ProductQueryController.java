package com.mycart.estore.ProductService.query.rest;

import com.mycart.estore.ProductService.query.FindProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<QueryProductRest> getProducts() {
        FindProductsQuery findProductsQuery = new FindProductsQuery();
        return queryGateway.query(findProductsQuery,
                ResponseTypes.multipleInstancesOf(QueryProductRest.class)).join();
    }
}
