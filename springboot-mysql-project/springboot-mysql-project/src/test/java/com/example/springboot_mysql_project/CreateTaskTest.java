package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.dto.TaskDTO;
import com.example.springboot_mysql_project.dto.TaskResponseDTO;
import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import com.example.springboot_mysql_project.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTaskTest {

    // create a fake repo
    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    // add fake repo into real service
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        TaskDTO inputDTO = new TaskDTO(null, "Learn Mockito", "Ongoing");
        Task savedTask = new Task();
        savedTask.setId(101L);
        savedTask.setDescription("Learn Mockito");

        TaskResponseDTO expectedResponse = new TaskResponseDTO(101L, "Learn Mockito", "Ongoing");

        // Mock mapping input to entity
        when(modelMapper.map(any(TaskDTO.class), eq(Task.class))).thenReturn(savedTask);
        // Mock saving returns the entity with ID
        when(taskRepo.save(any(Task.class))).thenReturn(savedTask);
        // Mock mapping entity back to Response DTO
        when(modelMapper.map(any(Task.class), eq(TaskResponseDTO.class))).thenReturn(expectedResponse);

        TaskResponseDTO result = taskService.createTask(inputDTO);

        assertEquals(101L, result.getId());
        assertEquals("Learn Mockito", result.getDescription());
    }
}