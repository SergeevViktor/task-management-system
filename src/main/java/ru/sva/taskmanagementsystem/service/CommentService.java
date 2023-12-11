package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.comment.CommentCreateDto;
import ru.sva.taskmanagementsystem.dto.comment.CommentDto;
import ru.sva.taskmanagementsystem.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findByTaskId(Long taskId);

    CommentDto createComment(Long taskId, CommentCreateDto commentCreateDto, String username);

    void deleteComment(Comment comment);

    String deleteCommentById(Long commentId);
}
