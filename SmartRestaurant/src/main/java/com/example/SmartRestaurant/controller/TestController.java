package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.dto.request.PrintJobTestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ping")
    @SendTo("/topic/pong")
    public String ping(String message) {
        System.out.println("Received: " + message);
        return "PONG: " + message;
    }

    @PostMapping("/send")
    public void send(@RequestBody PrintJobTestDto msg) {
        messagingTemplate.convertAndSend(
                "/host/1",
                msg
        );
    }

}
