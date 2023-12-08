package ru.sva.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.sva.taskmanagementsystem.model.User;

@Data
@AllArgsConstructor
public class TaskCreateDto {
    private String header;
    private String description;
    private String status;
    private String priority;
    private User executor;
}
