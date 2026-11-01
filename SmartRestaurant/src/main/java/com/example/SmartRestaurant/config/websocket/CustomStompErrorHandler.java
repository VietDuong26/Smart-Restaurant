package com.example.SmartRestaurant.config.websocket;

import org.jspecify.annotations.Nullable;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Component
public class CustomStompErrorHandler extends StompSubProtocolErrorHandler {
    @Override
    public @Nullable Message<byte[]> handleClientMessageProcessingError(@Nullable Message<byte[]> clientMessage, Throwable ex) {
        //method này mặc định là trả về ex.getMessage(nhưng ex của chúng ta ở method presend cần bọc thành
        // MessageDeliveryException và lấy cause là ex của ta vì vậy cần override lại để lấy cause)
        return super.handleClientMessageProcessingError(clientMessage, ex.getCause());
    }
}
