package ru.sva.taskmanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sva.taskmanagementsystem.dto.TaskCreateDto;
import ru.sva.taskmanagementsystem.dto.TaskDto;
import ru.sva.taskmanagementsystem.dto.TaskUpdateExecutor;
import ru.sva.taskmanagementsystem.dto.TaskUpdateStatus;
import ru.sva.taskmanagementsystem.service.TaskServiceImpl;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(Principal principal, @RequestBody TaskCreateDto taskCreateDto) {
        return ResponseEntity.created(URI.create("http://localhost:8080/task/create)"))
                .body(taskService.createTask(principal.getName(), taskCreateDto));
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@RequestParam Long taskId,
                                              @RequestBody TaskCreateDto taskCreateDto,
                                              Principal principal) {
        return ResponseEntity.ok().body(taskService.updateTask(taskCreateDto, taskId, principal.getName()));
    }

    @PutMapping("/update-executor/{taskId}")
    public ResponseEntity<TaskDto> updateExecutor(@RequestParam Long taskId,
                                                  @RequestBody TaskUpdateExecutor taskUpdateExecutor,
                                                  Principal principal) {
        return ResponseEntity.ok().body(taskService.updateExecutor(taskUpdateExecutor, taskId, principal.getName()));
    }

    @PutMapping("/update-status/{taskId}")
    public ResponseEntity<TaskDto> updateStatus(@RequestParam Long taskId,
                                                          TaskUpdateStatus taskUpdateStatus,
                                                          Principal principal) {
        return ResponseEntity.ok().body(taskService.updateStatus(taskUpdateStatus, taskId, principal.getName()));
    }

    @PutMapping("/update-status-by-executor/{taskId}")
    public ResponseEntity<TaskDto> updateStatusByExecutor(@RequestParam Long taskId,
                                                          TaskUpdateStatus taskUpdateStatus,
                                                          Principal principal) {
        return ResponseEntity.ok().body(taskService.updateStatusByExecutor(taskUpdateStatus, taskId, principal.getName()));
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@RequestParam Long taskId) {
        return ResponseEntity.ok().body(taskService.deleteTaskById(taskId));
    }

    @GetMapping("/view/{taskId}")
    public ResponseEntity<TaskDto> taskView(@RequestParam Long taskId) {
        return ResponseEntity.ok().body(taskService.taskView(taskId));
    }

    @GetMapping("/view-user-tasks")
    public ResponseEntity<List<TaskDto>> viewUserTasks(Principal principal) {
        return ResponseEntity.ok().body(taskService.viewUserTasks(principal.getName()));
    }

    @GetMapping("/view-user-tasks/{userId}")
    public ResponseEntity<List<TaskDto>> viewTasksByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok().body(taskService.viewTasksByUserId(userId));
    }
}
