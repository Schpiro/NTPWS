package com.NTPWS.projekt.repository.interfaces;

import com.NTPWS.projekt.model.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByEvent_Id(Long eventId);
}
