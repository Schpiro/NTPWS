package com.NTPWS.projekt.model.dto;

import com.NTPWS.projekt.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDTO {
    private final Long id;
    private final String title;
    private final String location;
    private final String date;
    private final String creator;
    private final Long creatorId;
    private final String details;
    private final String creationDate;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
