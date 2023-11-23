package com.NTPWS.projekt.service;

import com.NTPWS.projekt.repository.interfaces.CommentRepository;
import com.NTPWS.projekt.service.interfaces.CommentService;
import com.NTPWS.projekt.command.CommentCommand;
import com.NTPWS.projekt.model.dto.CommentDTO;
import com.NTPWS.projekt.model.pojo.Comment;
import com.NTPWS.projekt.model.pojo.Event;
import com.NTPWS.projekt.model.pojo.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentServiceImpl(CommentRepository commentRepository, EntityManager entityManager) {
        this.commentRepository = commentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<CommentDTO> findCommentForEvent(Long id) {
        return commentRepository.findAllByEvent_Id(id).stream().map(this::mapCommentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentDTO> createComment(CommentCommand commentCommand, Long id) {
        return Optional.of(mapCommentToDTO(commentRepository.save(mapCommandToComment(commentCommand,id))));
    }

    private Comment mapCommandToComment(CommentCommand commentCommand, Long id) {
        return new Comment(entityManager.getReference(User.class,commentCommand.getCreatorId()),
                commentCommand.getCommentBody(),
                commentCommand.getParentComment()!=null?entityManager.getReference(Comment.class,commentCommand.getParentComment()):null,
                entityManager.getReference(Event.class,id));
    }

    private CommentDTO mapCommentToDTO(Comment comment) {
        return new CommentDTO(comment.getId(),
                comment.getEvent().getId(),
                comment.getCreator().getUsername(),
                comment.getCreator().getId(),
                comment.getCommentBody(),
                comment.getCreationDate().toString(),
                comment.getParentComment()!=null? comment.getParentComment().getId():null);
    }
}
