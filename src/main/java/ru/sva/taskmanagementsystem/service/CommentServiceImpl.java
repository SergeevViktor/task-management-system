package ru.sva.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sva.taskmanagementsystem.dto.CommentCreateDto;
import ru.sva.taskmanagementsystem.dto.CommentDto;
import ru.sva.taskmanagementsystem.dto.mapper.CommentMapper;
import ru.sva.taskmanagementsystem.exception.NotFoundException;
import ru.sva.taskmanagementsystem.exception.ValidationException;
import ru.sva.taskmanagementsystem.model.Comment;
import ru.sva.taskmanagementsystem.model.Task;
import ru.sva.taskmanagementsystem.model.User;
import ru.sva.taskmanagementsystem.repository.CommentRepository;
import ru.sva.taskmanagementsystem.repository.TaskRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final TaskRepository taskRepository;

    @Override
    public CommentDto createComment(Long taskId, CommentCreateDto commentCreateDto, String username) {
        if (commentCreateDto.getText().isBlank() || commentCreateDto.getText().isEmpty()) {
            throw new ValidationException("Чтобы оставить комментарий, надо что-то написать!");
        }
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException("Такой задачи не существует!");
        }
        Task task = taskOptional.get();
        User user = userService.findByUsername(username).get();
        Comment comment = Comment.builder()
                .author(user)
                .text(commentCreateDto.getText())
                .taskToComment(task)
                .build();
        commentRepository.save(comment);

        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public String deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
        return "Success";
    }
}
