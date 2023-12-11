package ru.sva.taskmanagementsystem.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.sva.taskmanagementsystem.dto.task.TaskDto;
import ru.sva.taskmanagementsystem.dto.task.TaskDtoWithComments;
import ru.sva.taskmanagementsystem.model.Comment;
import ru.sva.taskmanagementsystem.model.Task;

import java.util.List;

@UtilityClass
public class TaskMapper {
    public TaskDto toTaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(UserMapper.toUserDto(task.getAuthor()))
                .executor(UserMapper.toUserDto(task.getExecutor()))
                .build();
        return taskDto;
    }

    public TaskDtoWithComments toTaskDtoComments(Task task, List<Comment> comments) {
        TaskDtoWithComments taskDto = TaskDtoWithComments.builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(UserMapper.toUserDto(task.getAuthor()))
                .executor(UserMapper.toUserDto(task.getExecutor()))
                .comments(CommentMapper.mapToListDto(comments))
                .build();

        return taskDto;
    }
}
