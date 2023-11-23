package com.NTPWS.projekt.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class EventCommand {
    @NotNull(message = "creator must not be empty")
    private Long creatorId;
    @NotNull(message = "title must not be empty")
    private String title;
    @NotNull(message = "details must not be empty")
    private String details;
    @NotNull(message = "date must not be empty")
    private String date;
    @NotNull(message = "location must not be empty")
    private String location;
}
