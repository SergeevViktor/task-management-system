package ru.sva.taskmanagementsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sva.taskmanagementsystem.dto.TaskCreateDto;
import ru.sva.taskmanagementsystem.dto.TaskDto;
import ru.sva.taskmanagementsystem.dto.TaskUpdateExecutor;
import ru.sva.taskmanagementsystem.dto.TaskUpdateStatus;
import ru.sva.taskmanagementsystem.dto.mapper.TaskMapper;
import ru.sva.taskmanagementsystem.exception.ConflictException;
import ru.sva.taskmanagementsystem.exception.ForbiddenException;
import ru.sva.taskmanagementsystem.exception.NotFoundException;
import ru.sva.taskmanagementsystem.exception.ValidationException;
import ru.sva.taskmanagementsystem.model.Priority;
import ru.sva.taskmanagementsystem.model.Status;
import ru.sva.taskmanagementsystem.model.Task;
import ru.sva.taskmanagementsystem.model.User;
import ru.sva.taskmanagementsystem.repository.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserServiceImpl userService;

    @Override
    public TaskDto createTask(String username, TaskCreateDto taskDto) {
        validateStatus(taskDto);
        validatePriority(taskDto);
        User user = existUserByUsername(username);
        Task task = Task.builder()
                .header(taskDto.getHeader())
                .description(taskDto.getDescription())
                .status(Status.valueOf(taskDto.getStatus()))
                .priority(Priority.valueOf(taskDto.getPriority()))
                .author(user)
                .executor(taskDto.getExecutor())
                .comments(null)
                .build();
        taskRepository.save(task);

        return TaskMapper.toTaskDto(task);
    }

    @Override
    public TaskDto updateTask(TaskCreateDto taskDto, Long taskId, String username) {
        Task task = existTask(taskId);
        authorMatch(username, task.getAuthor().getId());
        if (taskDto.getStatus() != null) {
            validateStatus(taskDto);
            task.setStatus(Status.valueOf(taskDto.getStatus()));
        }
        if (taskDto.getPriority() != null) {
            validatePriority(taskDto);
            task.setPriority(Priority.valueOf(taskDto.getPriority()));
        }
        if (taskDto.getHeader() != null) {
            task.setHeader(taskDto.getHeader());
        }
        if (taskDto.getDescription() != null) {
            task.setDescription(taskDto.getDescription());
        }
        taskRepository.save(task);

        return TaskMapper.toTaskDto(task);
    }

    @Override
    public TaskDto updateExecutor(TaskUpdateExecutor taskUpdateExecutor, Long taskId, String username) {
        Task task = existTask(taskId);
        authorMatch(username, task.getAuthor().getId());
        User user = existUserById(taskUpdateExecutor.getExecutorId());
        if (task.getExecutor().equals(user)) {
            throw new ConflictException("Данный пользователь уже стоит как испольнитель задачи!");
        }
        task.setExecutor(user);
        taskRepository.save(task);

        return TaskMapper.toTaskDto(task);
    }

    public TaskDto updateStatus(TaskUpdateStatus taskUpdateStatus, Long taskId, String username) {
        Task task = existTask(taskId);
        authorMatch(username, task.getAuthor().getId());
        task.setStatus(Status.valueOf(taskUpdateStatus.getStatus()));
        taskRepository.save(task);

        return TaskMapper.toTaskDto(task);
    }

    public TaskDto updateStatusByExecutor(TaskUpdateStatus taskUpdateStatus, Long taskId, String username) {
        Task task = existTask(taskId);
        User user = userService.findByUsername(username).get();
        if (!Objects.equals(user.getId(), task.getExecutor().getId())) {
            throw new ForbiddenException("Нет прав на изменение статуса задачи!");
        }
        task.setStatus(Status.valueOf(taskUpdateStatus.getStatus()));
        taskRepository.save(task);

        return TaskMapper.toTaskDto(task);
    }

    @Override
    public String deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
        return "Success";
    }

    @Override
    public TaskDto taskView(Long taskId) {
        Task task = existTask(taskId);

        return TaskMapper.toTaskDto(task);
    }

    public List<TaskDto> viewUserTasks(String username) {
        Long userId = existUserByUsername(username).getId();
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> viewTasksByUserId(Long userId) {
        existUserById(userId);
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream()
                .map(TaskMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    private User existUserByUsername(String username) {
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Такого пользователя не существует!");
        }

        return userOptional.get();
    }

    private User existUserById(Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("Такого пользователя не существует!");
        }

        return userOptional.get();
    }

    private Task existTask(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new NotFoundException("Такой задачи не существует!");
        }

        return taskOptional.get();
    }

    private void authorMatch(String username, Long authorId) {
        User user = userService.findByUsername(username).get();
        if (!Objects.equals(user.getId(), authorId)) {
            throw new ForbiddenException("Нет прав на изменение задачи!");
        }
    }

    private void validateStatus(TaskCreateDto taskDto) {
        String status = taskDto.getStatus();
        if (!Status.valueOf(status).equals(Status.PENDING) &&
                !Status.valueOf(status).equals(Status.IN_PROGRESS) &&
                    !Status.valueOf(status).equals(Status.DONE)) {
            throw new ValidationException("Неверно указан статус задачи!" +
                    "Допустимые значения: 'PENDING', 'IN_PROGRESS', 'DONE'.");
        }
    }

    private void validatePriority(TaskCreateDto taskDto) {
        String priority = taskDto.getPriority();
        if (Priority.valueOf(priority).equals(Priority.HIGH) &&
                Priority.valueOf(priority).equals(Priority.MEDIUM) &&
                Priority.valueOf(priority).equals(Priority.LOW)) {
            throw new ValidationException("Неверно указан приоритет задачи!" +
                    "Допустимые значения: 'HIGH', 'MEDIUM', 'LOW'.");
        }
    }
}
