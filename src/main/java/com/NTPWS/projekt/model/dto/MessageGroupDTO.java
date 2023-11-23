package com.NTPWS.projekt.model.dto;

import com.NTPWS.projekt.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MessageGroupDTO {
    private Long id;
    private String groupName;
    private List<Long> groupParticipants;


    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }
}
