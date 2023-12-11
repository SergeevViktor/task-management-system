package ru.sva.taskmanagementsystem.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.sva.taskmanagementsystem.dto.comment.CommentDto;
import ru.sva.taskmanagementsystem.dto.user.UserDto;
import ru.sva.taskmanagementsystem.model.Priority;
import ru.sva.taskmanagementsystem.model.Status;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class TaskDtoWithComments {
    private Long id;
    private String header;
    private String description;
    private Status status;
    private Priority priority;
    private UserDto author;
    private UserDto executor;
    private Collection<CommentDto> comments;
}
