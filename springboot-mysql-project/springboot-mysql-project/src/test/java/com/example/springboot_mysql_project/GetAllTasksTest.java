package com.example.springboot_mysql_project;

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
import java.lang.reflect.Type;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllTasksTest {

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testGetAllTasks(){
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Test Task");
        List<Task> taskList = List.of(task);

        TaskResponseDTO responseDTO = new TaskResponseDTO(1L, "Test Task", "Pending");
        List<TaskResponseDTO> responseList = List.of(responseDTO);

        when(taskRepo.findAll()).thenReturn(taskList);

        when(modelMapper.map(eq(taskList), any(Type.class))).thenReturn(responseList);

        List<TaskResponseDTO> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
}