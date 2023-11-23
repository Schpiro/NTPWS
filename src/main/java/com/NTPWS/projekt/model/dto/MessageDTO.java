package com.NTPWS.projekt.model.dto;

import com.NTPWS.projekt.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MessageDTO {
    private final Long creatorId;
    private final String creator;
    private final Long recipientId;
    private final Long recipientGroupId;
    private final List<Long> groupParticipantsIds;
    private final String creationDate;
    private final String messageBody;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
