package com.example.task_management.task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private LocalDateTime createdAt;
    private String title;
    private String description;

    //response only
    private Long id;
}
