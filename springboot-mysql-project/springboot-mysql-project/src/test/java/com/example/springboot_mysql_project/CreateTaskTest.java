package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.dto.TaskDTO;
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
        Task mappedTask = new Task();
        mappedTask.setDescription("Learn Mockito");

        // Mock mapping DTO to Entity
        when(modelMapper.map(any(TaskDTO.class), eq(Task.class))).thenReturn(mappedTask);
        // Mock saving the entity
        when(taskRepo.save(any(Task.class))).thenReturn(mappedTask);

        TaskDTO result = taskService.createTask(inputDTO);

        assertEquals("Learn Mockito", result.getDescription());
        verify(taskRepo, times(1)).save(any(Task.class));
    }
}