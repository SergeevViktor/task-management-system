package ru.sva.taskmanagementsystem.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskCreateDto {
    private String header;
    private String description;
    private String status;
    private String priority;
}
