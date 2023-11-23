package com.NTPWS.projekt.handler;

import com.NTPWS.projekt.NTPWSApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class SocketHandler extends TextWebSocketHandler {
    private final EventHandler eventHandler;
    private static final Logger logger = LogManager.getLogger(NTPWSApplication.class);

    public SocketHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        logger.info(session.getId()+"sent a message:"+message.getPayload());
        eventHandler.handleMessage(session, message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("Connected client: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Connection closed: " + session.getId());
        eventHandler.closeConnection(session);
    }
}