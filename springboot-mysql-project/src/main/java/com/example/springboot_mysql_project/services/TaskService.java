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
        return modelMapper.map(taskList, new TypeToken<List<TaskResponseDTO>>() {}.getType());
    }

    public TaskResponseDTO createTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepo.save(task);
        return modelMapper.map(savedTask, TaskResponseDTO.class);
    }

    public TaskResponseDTO updateTask(TaskDTO taskDTO) {
        if (taskRepo.existsById(taskDTO.getId())) {
            // Map DTO to Entity
            Task task = modelMapper.map(taskDTO, Task.class);
            // Save Entity
            Task savedTask = taskRepo.save(task);
            // Map saved Entity back to ResponseDTO
            return modelMapper.map(savedTask, TaskResponseDTO.class);
        } else {
            return null;
        }
    }

    public String deleteTask(Long id) {
        if (taskRepo.existsById(id)) {
            taskRepo.deleteById(id);
            return "Task deleted successfully";
        }
        return "Task not found";
    }
}