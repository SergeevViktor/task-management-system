package ru.sva.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskCreateDto {
    private String header;
    private String description;
    private String status;
    private String priority;
    //private Long executor;
}
