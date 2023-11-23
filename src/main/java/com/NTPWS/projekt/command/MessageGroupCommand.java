package com.NTPWS.projekt.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
public class MessageGroupCommand {
    @NotNull(message = "Group must have name!")
    private String groupName;

    @NotNull(message = "Must have at least one group member")
    private List<Long> groupParticipants;
}
