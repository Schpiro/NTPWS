package com.NTPWS.projekt.repository.interfaces;

import com.NTPWS.projekt.model.pojo.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
