package com.example.springboot_mysql_project.repos;

import com.example.springboot_mysql_project.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// Takes the type of object and the type of the ID
public interface TaskRepo extends JpaRepository<Task, Long> {
}