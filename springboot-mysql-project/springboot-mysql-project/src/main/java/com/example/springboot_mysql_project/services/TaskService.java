package com.example.springboot_mysql_project.services;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());

        return taskRepo.save(existingTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepo.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepo.deleteById(id);
    }
}
