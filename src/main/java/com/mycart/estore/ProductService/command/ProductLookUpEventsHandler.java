package com.mycart.estore.ProductService.command;

import com.mycart.estore.ProductService.core.data.ProductLookUpEntity;
import com.mycart.estore.ProductService.core.events.ProductCreatedEvent;
import com.mycart.estore.ProductService.core.repository.ProductLookUpRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductLookUpEventsHandler {

    private final ProductLookUpRepository productLookUpRepository;
    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookUpEntity productLookUpEntity = ProductLookUpEntity.builder()
                .productId(event.getProductId())
                .title(event.getTitle()).build();
        productLookUpRepository.save(productLookUpEntity);
    }
}
