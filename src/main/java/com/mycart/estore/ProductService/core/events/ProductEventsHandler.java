package com.mycart.estore.ProductService.core.events;

import com.mycart.estore.ProductService.core.data.ProductEntity;
import com.mycart.estore.ProductService.core.repository.ProductRepository;
import com.mycart.estore.core.events.ProductReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("product-group")
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    @ExceptionHandler(resultType = Exception.class)
    private void handle(Exception exception) throws Exception {
        throw exception;
    }

    @ExceptionHandler(resultType = IllegalArgumentException.class)
    private void handle(IllegalArgumentException exception) {
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        try {
            productRepository.save(productEntity);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        ProductEntity byProductId = productRepository.findByProductId(productReservedEvent.getProductId());
        byProductId.setQuantity(byProductId.getQuantity() - productReservedEvent.getQuantity());
        productRepository.save(byProductId);

        log.info("ProductReservedEvent is called for productId {} and orderId {}",productReservedEvent.getProductId()
                ,productReservedEvent.getOrderId()
        );
    }
}
