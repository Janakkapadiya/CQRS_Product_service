package com.mycart.estore.ProductService.core.repository;

import com.mycart.estore.ProductService.core.data.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId,String productTitle);
}
