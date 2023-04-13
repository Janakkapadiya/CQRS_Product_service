package com.mycart.estore.ProductService.core.repository;

import com.mycart.estore.ProductService.core.data.ProductLookUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductLookUpRepository extends JpaRepository<ProductLookUpEntity,String> {
    ProductLookUpEntity findByProductIdOrTitle(String title,String productId);
}
