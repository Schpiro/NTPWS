package com.NTPWS.projekt.controller;

import com.NTPWS.projekt.NTPWSApplication;
import com.NTPWS.projekt.command.MessageCommand;
import com.NTPWS.projekt.model.dto.MessageDTO;
import com.NTPWS.projekt.model.pojo.UserAuthentication;
import com.NTPWS.projekt.service.interfaces.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("message")
@CrossOrigin(origins = "https://localhost:4200")
public class MessageController {
    private static final Logger logger = LogManager.getLogger(NTPWSApplication.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageDTO> getAllMessages(Principal principal) {
        logger.info(((UserAuthentication) principal).getPrincipal().getUID() + " fetching all messages for user.");
        return messageService.findAllByUser(((UserAuthentication) principal).getPrincipal().getUID());
    }

    @GetMapping("/{id}")
    public List<MessageDTO> getConversationWithUser(Principal principal, @PathVariable("id") Long senderId) {
        logger.info(((UserAuthentication) principal).getPrincipal().getUID());
        return messageService.getConversationWithUser(((UserAuthentication) principal).getPrincipal().getUID(), senderId);
    }

    @GetMapping("/group/{id}")
    public List<MessageDTO> getAllMessagesByGroup(Principal principal, @PathVariable("id") Long groupId) {
        logger.info("Fetching group (" + groupId + ") messages for user" + ((UserAuthentication) principal).getPrincipal().getUID());
        return messageService.findAllByGroup(groupId);
    }

    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(@Valid @RequestBody final MessageCommand messageCommand, Principal principal) {
        logger.info(principal);
        return messageService.sendMessage(messageCommand, ((UserAuthentication) principal).getPrincipal().getUID())
                .map(
                        MessageDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(MessageDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }
}
