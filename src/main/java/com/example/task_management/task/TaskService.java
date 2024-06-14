package com.example.task_management.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                             .stream().map(this::toTaskDto)
                             .toList();
    }

    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                             .map(this::toTaskDto)
                             .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskDto createTask(TaskDto taskDetails) {
        Task task = new Task();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCreatedAt(LocalDateTime.now());
        Task save = taskRepository.save(task);
        return toTaskDto(save);
    }

    public TaskDto updateTask(Long id, TaskDto taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        Task updated = taskRepository.save(task);
        return toTaskDto(updated);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskDto toTaskDto(Task e) {
        TaskDto dto = new TaskDto();
        dto.setId(e.getId());
        dto.setTitle(e.getTitle());
        dto.setDescription(e.getDescription());
        dto.setCreatedAt(e.getCreatedAt());

        return dto;
    }
}
