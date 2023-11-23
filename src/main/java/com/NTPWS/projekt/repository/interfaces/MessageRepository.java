package com.NTPWS.projekt.repository.interfaces;

import com.NTPWS.projekt.model.pojo.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRecipient_Id(Long recipientId);

    @Query("SELECT msg FROM Message msg WHERE msg.creator.id=:creator and msg.recipient.id=:recipientId or msg.creator.id=:recipientId and msg.recipient.id=:creator")
    List<Message> fetchConversation(Long recipientId, Long creator);

    List<Message> findAllByRecipientGroup_Id(Long groupId);
}