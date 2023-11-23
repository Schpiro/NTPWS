package com.NTPWS.projekt.controller;

import com.NTPWS.projekt.NTPWSApplication;
import com.NTPWS.projekt.command.CommentCommand;
import com.NTPWS.projekt.command.EventCommand;
import com.NTPWS.projekt.model.dto.CommentDTO;
import com.NTPWS.projekt.model.dto.EventDTO;
import com.NTPWS.projekt.service.interfaces.CommentService;
import com.NTPWS.projekt.service.interfaces.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("event")
@CrossOrigin(origins = "https://localhost:4200")
public class EventController {
    private static final Logger logger = LogManager.getLogger(NTPWSApplication.class);

    private final EventService eventService;
    private final CommentService commentService;

    public EventController(EventService eventService, CommentService commentService) {
        this.eventService = eventService;
        this.commentService = commentService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody final EventCommand eventCommand, Principal principal) {
        logger.info(principal);
        return eventService.createEvent(eventCommand)
                .map(
                        EventDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(EventDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @GetMapping("/comments/{id}")
    public List<CommentDTO> getCommentsForEvent(@PathVariable("id") Long id) {
        return commentService.findCommentForEvent(id);
    }

    @PostMapping("/comments/{id}")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody final CommentCommand commentCommand, @PathVariable("id") Long id) {
        return commentService.createComment(commentCommand, id)
                .map(
                        CommentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(CommentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
    }
}
