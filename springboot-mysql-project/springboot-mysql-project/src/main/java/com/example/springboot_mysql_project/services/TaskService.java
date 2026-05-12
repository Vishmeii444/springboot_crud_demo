package com.example.springboot_mysql_project.services;

import com.example.springboot_mysql_project.dto.TaskDTO;
import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<TaskDTO> getAllTasks() {
        List<Task> taskList = taskRepo.findAll();
        // Converts the List of Entities to a List of DTOs
        return modelMapper.map(taskList, new TypeToken<List<TaskDTO>>() {}.getType());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        // Map DTO to Entity to save it
        Task task = modelMapper.map(taskDTO, Task.class);
        taskRepo.save(task);
        return taskDTO;
    }

    public TaskDTO updateTask(TaskDTO taskDTO) {
        // ModelMapper finds the entity by ID and updates fields
        taskRepo.save(modelMapper.map(taskDTO, Task.class));
        return taskDTO;
    }

    public String deleteTask(TaskDTO taskDTO) {
        taskRepo.delete(modelMapper.map(taskDTO, Task.class));
        return "Task deleted successfully";
    }
}