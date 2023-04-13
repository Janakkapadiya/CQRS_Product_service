package com.mycart.estore.ProductService.command.interceptor;

import com.mycart.estore.ProductService.command.CreateProductCommand;
import com.mycart.estore.ProductService.core.data.ProductLookUpEntity;
import com.mycart.estore.ProductService.core.repository.ProductLookUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookUpRepository productLookUpRepository;
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (index,command) -> {

            log.info("Intercepted Command: {}" , command.getPayloadType());

            if(CreateProductCommand.class.equals(command.getPayloadType()))
            {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                ProductLookUpEntity productLookUpEntity = productLookUpRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if(productLookUpEntity != null)
                {
                    throw new IllegalStateException(
                            String.format("Product with %s id and %s title already exists",
                                    createProductCommand.getProductId(),createProductCommand.getTitle()));

                }
            }
            return command;
        };
    }
}
