package com.NTPWS.projekt.service.interfaces;

import com.NTPWS.projekt.command.EventCommand;
import com.NTPWS.projekt.model.dto.EventDTO;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDTO> findAll();

    Optional<EventDTO> createEvent(EventCommand eventCommand);

    void deleteEvent(Long id);
}
