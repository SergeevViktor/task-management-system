package ru.sva.taskmanagementsystem.service;

import ru.sva.taskmanagementsystem.dto.TaskCreateDto;
import ru.sva.taskmanagementsystem.dto.TaskDto;
import ru.sva.taskmanagementsystem.dto.TaskUpdateExecutor;
import ru.sva.taskmanagementsystem.dto.TaskUpdateStatus;
import ru.sva.taskmanagementsystem.model.Task;

import java.util.List;

public interface TaskService {
    Task findById(Long taskId);
    TaskDto createTask(String username, TaskCreateDto taskDto);

    TaskDto updateTask(TaskCreateDto taskDto, Long taskId, String username);

    TaskDto updateExecutor(TaskUpdateExecutor taskUpdateExecutor, Long taskId, String username);

    TaskDto updateStatus(TaskUpdateStatus taskUpdateStatus, Long taskId, String username);

    TaskDto updateStatusByExecutor(TaskUpdateStatus taskUpdateStatus, Long taskId, String username);

    String deleteTaskById(Long taskId);

    TaskDto taskView(Long taskId);

    List<TaskDto> viewUserTasks(String username);

    List<TaskDto> viewTasksByUserId(Long userId);
}
