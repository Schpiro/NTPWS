package com.NTPWS.projekt.repository.interfaces;

import com.NTPWS.projekt.model.pojo.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
    List<MessageGroup> findAllByGroupParticipant_Id(Long recipientId);
}
