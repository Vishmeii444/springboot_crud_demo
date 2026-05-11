package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import com.example.springboot_mysql_project.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTaskTest {

    // create a fake repo
    @Mock
    private TaskRepo taskRepo;

    // add fake repo into real service
    @InjectMocks
    private TaskService taskService;

    @Test
    public void testCreateTask() {

        // creeate the new task
        Task inputTask = new Task();
        inputTask.setDescription("Learn Mockito");
        inputTask.setStatus("Ongoing");

        // save the new task
        Task savedTask = new Task();
        savedTask.setId(101L);
        savedTask.setDescription("Learn Mockito");
        savedTask.setStatus("Ongoing");

        // this will basically return the saved task that's manually created above
        when(taskRepo.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(inputTask);

        assertNotNull(result.getId());
        assertEquals(101L, result.getId());
        assertEquals("Learn Mockito", result.getDescription());

        verify(taskRepo, times(1)).save(any(Task.class));
    }
}