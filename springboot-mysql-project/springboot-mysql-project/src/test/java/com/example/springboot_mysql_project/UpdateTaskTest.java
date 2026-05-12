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
public class UpdateTaskTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testUpdateTask() {
        TaskDTO updateDTO = new TaskDTO(1L, "New Description", "Complete");
        Task mappedTask = new Task();
        mappedTask.setId(1L);

        when(modelMapper.map(any(TaskDTO.class), eq(Task.class))).thenReturn(mappedTask);
        when(taskRepo.save(any(Task.class))).thenReturn(mappedTask);

        TaskDTO result = taskService.updateTask(updateDTO);

        assertEquals("New Description", result.getDescription());
        verify(taskRepo, times(1)).save(mappedTask);
    }
}