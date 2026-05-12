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
import org.modelmapper.TypeToken;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllTasksTest {

    // create the fake repo
    @Mock
    private TaskRepo taskRepo;

    @Mock
    private ModelMapper modelMapper;

    // inject the fake repo into the real service
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testGetAllTasks(){
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Go through JUnit");
        List<Task> taskList = List.of(task);

        TaskResponseDTO responseDTO = new TaskResponseDTO(1L, "Go through JUnit", "Pending");
        List<TaskResponseDTO> responseList = List.of(responseDTO);

        when(taskRepo.findAll()).thenReturn(taskList);
        // Mock the mapping to TaskResponseDTO list
        when(modelMapper.map(eq(taskList), any())).thenReturn(responseList);

        List<TaskResponseDTO> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
}