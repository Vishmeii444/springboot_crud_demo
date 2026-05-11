package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.entities.Task;
import com.example.springboot_mysql_project.repos.TaskRepo;
import com.example.springboot_mysql_project.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateTaskTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testUpdateTask() {
        // create an existing task and the updated data
        Task existingTask = new Task();
        existingTask.setId(1L);
        existingTask.setDescription("Old Description");
        existingTask.setStatus("Ongoing");

        Task updatedDetails = new Task();
        updatedDetails.setDescription("New Description");
        updatedDetails.setStatus("Complete");

        // findById returns the old task, save returns the result
        when(taskRepo.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepo.save(any(Task.class))).thenReturn(existingTask);

        // call the service method
        Task result = taskService.updateTask(1L, updatedDetails);

        // check if the fields were changed correctly
        assertEquals("New Description", result.getDescription());
        assertEquals("Complete", result.getStatus());

        verify(taskRepo, times(1)).save(existingTask);
    }
}