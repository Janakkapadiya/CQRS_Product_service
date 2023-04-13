package com.mycart.estore.ProductService.query;

import com.mycart.estore.ProductService.core.data.ProductEntity;
import com.mycart.estore.ProductService.core.repository.ProductRepository;
import com.mycart.estore.ProductService.query.rest.QueryProductRest;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductRepository productRepository;
    @QueryHandler
    public List<QueryProductRest> getProducts(FindProductsQuery query){
        List<QueryProductRest> products = new ArrayList<>();
        List<ProductEntity> storeProducts = productRepository.findAll();
        for(ProductEntity product : storeProducts)
        {
            QueryProductRest queryProduct = new QueryProductRest();
            BeanUtils.copyProperties(product,queryProduct);
            products.add(queryProduct);
        }
        return products;
    }
}
