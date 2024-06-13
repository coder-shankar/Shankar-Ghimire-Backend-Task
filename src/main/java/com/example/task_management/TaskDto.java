package com.example.task_management;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {
    private LocalDateTime createdAt;
    private String title;
    private String description;
    private boolean completed;

    //response only
    private Long id;
}
