package com.example.springboot_mysql_project;

import org.springframework.data.jpa.repository.JpaRepository;

// Takes the type of object and the type of the ID
public interface TaskRepo extends JpaRepository<Task, Long> {
}