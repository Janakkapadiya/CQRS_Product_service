package com.mycart.estore.ProductService.command.interceptor;

import com.mycart.estore.ProductService.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (index,command) -> {

            log.info("Intercepted Command: {}" , command.getPayloadType());

            if(CreateProductCommand.class.equals(command.getPayloadType()))
            {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0)
                {
                    throw new IllegalArgumentException("Price can not be less then ot equals to zero");
                }

                if(createProductCommand.getTitle().isBlank() || createProductCommand.getTitle() == null)
                {
                    throw new IllegalArgumentException("title can not be empty");
                }
            }
            return command;
        };
    }
}
