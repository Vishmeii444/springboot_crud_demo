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
public class DeleteTaskTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testDeleteTask() {
        TaskDTO deleteDTO = new TaskDTO(1L, "Test Task", "Done");
        Task mappedTask = new Task();
        mappedTask.setId(1L);

        when(modelMapper.map(any(TaskDTO.class), eq(Task.class))).thenReturn(mappedTask);

        String result = taskService.deleteTask(deleteDTO);

        assertEquals("Task deleted successfully", result);
        verify(taskRepo, times(1)).delete(mappedTask);
    }
}