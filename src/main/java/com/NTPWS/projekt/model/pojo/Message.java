package com.NTPWS.projekt.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;
    private String messageBody;
    private LocalDateTime createDate;
    @ManyToOne(targetEntity = Message.class)
    @JoinColumn(name = "parent_message_id")
    private Message parentMessage;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne(targetEntity = MessageGroup.class)
    @JoinColumn(name = "recipient_group_id")
    private MessageGroup recipientGroup;

    public Message(User creator, String messageBody, Message parentMessage, User recipient, MessageGroup recipientGroup) {
        this.creator = creator;
        this.messageBody = messageBody;
        this.createDate = LocalDateTime.now();
        this.parentMessage = parentMessage;
        this.recipient = recipient;
        this.recipientGroup = recipientGroup;
    }
}
