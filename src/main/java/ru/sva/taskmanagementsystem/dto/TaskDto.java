package ru.sva.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.sva.taskmanagementsystem.model.Comment;
import ru.sva.taskmanagementsystem.model.Priority;
import ru.sva.taskmanagementsystem.model.Status;
import ru.sva.taskmanagementsystem.model.User;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String header;
    private String description;
    private Status status;
    private Priority priority;
    private User author;
    private User executor;
    private Collection<Comment> comments;
}
