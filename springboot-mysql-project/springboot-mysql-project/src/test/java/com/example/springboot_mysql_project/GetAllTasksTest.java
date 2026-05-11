package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import com.example.springboot_mysql_project.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllTasksTest {

    // create the fake repo
    @Mock
    private TaskRepo taskRepo;

    // inject the fake repo into the real service
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testGetAllTasks(){

        // tells fake repo what to do
        Task task = new Task();
        task.setDescription("Go through JUnit");
        when(taskRepo.findAll()).thenReturn(List.of(task));

        // call the service method
        List<Task> result = taskService.getAllTasks();

        // check if it works
        assertEquals(1, result.size());
        assertEquals("Go through JUnit", result.get(0).getDescription());
    }
}
