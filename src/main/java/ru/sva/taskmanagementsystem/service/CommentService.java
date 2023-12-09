package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.CommentCreateDto;
import ru.sva.taskmanagementsystem.dto.CommentDto;
import ru.sva.taskmanagementsystem.model.Comment;

public interface CommentService {

    CommentDto createComment(Long taskId, CommentCreateDto commentCreateDto, String username);

    void deleteComment(Comment comment);

    String deleteCommentById(Long commentId);
}
