package com.NTPWS.projekt.handler;

import com.NTPWS.projekt.model.dto.*;
import com.NTPWS.projekt.model.enums.MessageTypes;
import com.NTPWS.projekt.util.GsonUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventHandler {
    private static final Map<Long, WebSocketSession> openSessions = new HashMap<>();

    public void handleMessage(WebSocketSession session, String message) throws IOException {
        WebSocketMessageDTO websocketMessageDTO = GsonUtil.fromJson(message, WebSocketMessageDTO.class);
        switch (websocketMessageDTO.getType()) {
            case CLIENT_ID -> addSession(session, GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), Long.class));
            case PRIVATE_MESSAGE -> sendPrivateMessage(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), MessageDTO.class), false);
            case GROUP_MESSAGE -> sendPrivateMessage(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), MessageDTO.class), true);
            case NEW_GROUP -> newGroupCreated(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), MessageGroupDTO.class));
            case OFFER, ANSWER,ICE_CANDIDATE, END_CALL-> sendRTCWebSocketMessage(websocketMessageDTO.getPayload(),websocketMessageDTO.getType(),websocketMessageDTO.getRecipientIds(),websocketMessageDTO.getSenderId(),websocketMessageDTO.getSenderName());
            case NEW_EVENT -> sendWebSocketMessage(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), EventDTO.class),MessageTypes.NEW_EVENT,openSessions.keySet().stream().toList());
            case NEW_COMMENT -> sendWebSocketMessage(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), CommentDTO.class),MessageTypes.NEW_COMMENT,openSessions.keySet().stream().toList());
            case DELETE_EVENT -> sendWebSocketMessage(GsonUtil.fromJson(websocketMessageDTO.getPayload().toString(), EventDTO.class),MessageTypes.DELETE_EVENT,openSessions.keySet().stream().toList());
        }
    }

    private <T> void sendRTCWebSocketMessage(T message, MessageTypes messageTypes, List<Long> userIds, Long senderId, String senderName) throws IOException {
        for (Long userId : userIds) {
            if (openSessions.containsKey(userId))
                openSessions.get(userId).sendMessage(new TextMessage(new WebSocketMessageDTO(messageTypes, message, senderId, senderName).toString()));
            else
                openSessions.get(senderId).sendMessage(new TextMessage(new WebSocketMessageDTO(MessageTypes.END_CALL,"User is not online", 0L).toString()));
        }
    }

    private void sendPrivateMessage(MessageDTO message, boolean groupMessage) throws IOException {
        List<Long> userIds = new ArrayList<>() {
            {
                if (groupMessage) addAll(message.getGroupParticipantsIds());
                else {
                    add(message.getCreatorId());
                    add(message.getRecipientId());
                }
            }
        };
        sendWebSocketMessage(message, MessageTypes.NEW_MESSAGE, userIds);
    }

    private void newGroupCreated(MessageGroupDTO messageGroup) throws IOException {
        List<Long> userIds = messageGroup.getGroupParticipants();
        sendWebSocketMessage(messageGroup, MessageTypes.NEW_GROUP, userIds);
    }

    private <T> void sendWebSocketMessage(T message, MessageTypes messageTypes, List<Long> userIds) throws IOException {
        for (Long userId : userIds) {
            if (openSessions.containsKey(userId))
                openSessions.get(userId).sendMessage(new TextMessage(new WebSocketMessageDTO(messageTypes, message).toString()));
        }
    }

    private void addSession(WebSocketSession session, Long userId) {
        openSessions.put(userId, session);
    }

    public void closeConnection(WebSocketSession session) {
        openSessions.values().remove(session);
    }
}
