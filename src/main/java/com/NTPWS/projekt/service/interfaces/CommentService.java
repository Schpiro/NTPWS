package com.NTPWS.projekt.service.interfaces;

import com.NTPWS.projekt.command.CommentCommand;
import com.NTPWS.projekt.model.dto.CommentDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDTO> findCommentForEvent(Long id);

    Optional<CommentDTO> createComment(CommentCommand commentCommand, Long id);

}
