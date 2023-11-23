package com.NTPWS.projekt.model.dto;

import com.NTPWS.projekt.model.enums.MessageTypes;
import com.NTPWS.projekt.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WebSocketMessageDTO {
    private MessageTypes type;
    private Object payload;
    private Long senderId;
    private List<Long> recipientIds;
    private String senderName;

    public WebSocketMessageDTO(MessageTypes messageTypes, Object message) {
        this.type=messageTypes;
        this.payload=message;
    }
    public WebSocketMessageDTO(MessageTypes messageTypes, Object message, Long senderId) {
        this.type=messageTypes;
        this.payload=message;
        this.senderId=senderId;
    }
    public WebSocketMessageDTO(MessageTypes messageTypes, Object message, Long senderId, String senderName) {
        this.type=messageTypes;
        this.payload=message;
        this.senderId=senderId;
        this.senderName=senderName;
    }


    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
