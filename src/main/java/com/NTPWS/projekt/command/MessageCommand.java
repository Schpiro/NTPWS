package com.NTPWS.projekt.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class MessageCommand {
    @NotNull(message = "messageBody must not be empty")
    private String messageBody;

    private Long parentMessage;

    private Long recipientId;

    private Long recipientGroupId;

    private Long creatorId;

    @AssertTrue
    private boolean recipientExists() {
        return recipientId != null || recipientGroupId != null;
    }
}
