package com.example.springboot_mysql_project;

import com.example.springboot_mysql_project.repos.TaskRepo;
import com.example.springboot_mysql_project.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteTaskTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void testDeleteTask() {
        // let the fake repo know that the ID exists
        Long taskId = 1L;
        when(taskRepo.existsById(taskId)).thenReturn(true);

        // call the delete method
        taskService.deleteTask(taskId);

        // verify that the deleteById method was actually triggered
        verify(taskRepo, times(1)).deleteById(taskId);
    }
}