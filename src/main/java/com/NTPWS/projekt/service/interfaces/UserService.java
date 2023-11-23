package com.NTPWS.projekt.service.interfaces;

import com.NTPWS.projekt.command.MessageGroupCommand;
import com.NTPWS.projekt.model.dto.MessageGroupDTO;
import com.NTPWS.projekt.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAllUsers();

    List<MessageGroupDTO> findAllGroups(Long userId);

    MessageGroupDTO saveMessageGroup(MessageGroupCommand messageGroupCommand);

    List<UserDTO> getUsersInGroup(Long groupId);
}
