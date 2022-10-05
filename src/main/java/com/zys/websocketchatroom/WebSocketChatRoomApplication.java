package com.zys.websocketchatroom;

import com.zys.websocketchatroom.websocket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebSocketChatRoomApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocketChatRoomApplication.class, args);
        // 在启动类中传入上下文
        WebSocketServer.setApplicationContext(applicationContext);
    }

}
