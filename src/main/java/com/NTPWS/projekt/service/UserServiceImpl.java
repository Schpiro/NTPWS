package com.NTPWS.projekt.service;

import com.NTPWS.projekt.repository.interfaces.MessageGroupRepository;
import com.NTPWS.projekt.repository.interfaces.UserRepository;
import com.NTPWS.projekt.service.interfaces.UserService;
import com.NTPWS.projekt.command.MessageGroupCommand;
import com.NTPWS.projekt.model.dto.MessageGroupDTO;
import com.NTPWS.projekt.model.dto.UserDTO;
import com.NTPWS.projekt.model.pojo.MessageGroup;
import com.NTPWS.projekt.model.pojo.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MessageGroupRepository messageGroupRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository, MessageGroupRepository messageGroupRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.messageGroupRepository = messageGroupRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageGroupDTO> findAllGroups(Long userId) {
        return messageGroupRepository.findAllByGroupParticipant_Id(userId).stream().map(this::mapGroupToDTO).collect(Collectors.toList());
    }

    @Override
    public MessageGroupDTO saveMessageGroup(MessageGroupCommand messageGroupCommand) {
        MessageGroup messageGroup = mapCommandToMessageGroup(messageGroupCommand);
        messageGroupRepository.save(messageGroup);
        return mapGroupToDTO(messageGroup);
    }

    @Override
    public List<UserDTO> getUsersInGroup(Long groupId) {
        return userRepository.findByGroups_Id(groupId).stream().map(this::mapUserToDTO).collect(Collectors.toList());
    }

    private UserDTO mapUserToDTO(final User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    private MessageGroupDTO mapGroupToDTO(final MessageGroup messageGroup) {
        return new MessageGroupDTO(messageGroup.getId(), messageGroup.getName(),messageGroup.getGroupParticipant().stream().map(User::getId).collect(Collectors.toList()));
    }

    private MessageGroup mapCommandToMessageGroup(MessageGroupCommand messageGroupCommand){
        List<Long> userIds = messageGroupCommand.getGroupParticipants();
        String groupName = messageGroupCommand.getGroupName();
        List<User> listOfUsers = new ArrayList<>();
        for (Long userId: userIds) listOfUsers.add(entityManager.getReference(User.class, userId));
        return new MessageGroup(groupName,listOfUsers);
    }

}
