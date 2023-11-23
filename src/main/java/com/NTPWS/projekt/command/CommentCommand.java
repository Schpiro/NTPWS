package com.NTPWS.projekt.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CommentCommand {
    @NotNull(message = "creator must not be empty")
    private Long creatorId;
    @NotNull(message = "commentBody must not be empty")
    private String commentBody;
    @Nullable
    private Long parentComment;
}
