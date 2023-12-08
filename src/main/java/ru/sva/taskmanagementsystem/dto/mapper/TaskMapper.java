package ru.sva.taskmanagementsystem.dto.mapper;

import lombok.experimental.UtilityClass;
import ru.sva.taskmanagementsystem.dto.TaskDto;
import ru.sva.taskmanagementsystem.model.Task;

@UtilityClass
public class TaskMapper {
    public TaskDto toTaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .header(task.getHeader())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .author(task.getAuthor())
                .executor(task.getExecutor())
                .comments(task.getComments())
                .build();
        return taskDto;
    }
}
