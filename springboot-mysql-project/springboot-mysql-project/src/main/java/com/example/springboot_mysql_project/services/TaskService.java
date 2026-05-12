package com.example.springboot_mysql_project.services;

import com.example.springboot_mysql_project.dto.TaskDTO;
import com.example.springboot_mysql_project.dto.TaskResponseDTO;
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

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> taskList = taskRepo.findAll();
        // Converts the List of Entities to a List of DTOs
        return modelMapper.map(taskList, new TypeToken<List<TaskResponseDTO>>() {}.getType());
    }

    public TaskResponseDTO createTask(TaskDTO taskDTO) {
        // Map DTO to Entity to save it
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepo.save(task);
        return modelMapper.map(savedTask, TaskResponseDTO.class);
    }

    public TaskResponseDTO updateTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task updatedTask = taskRepo.save(task);
        return modelMapper.map(updatedTask, TaskResponseDTO.class);
    }

    public String deleteTask(TaskDTO taskDTO) {
        taskRepo.delete(modelMapper.map(taskDTO, Task.class));
        return "Task deleted successfully";
    }
}