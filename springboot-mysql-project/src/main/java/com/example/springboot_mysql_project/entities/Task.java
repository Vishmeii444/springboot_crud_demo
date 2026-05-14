package com.example.springboot_mysql_project.entities;

/*
* JPA entity that maps to a database table for storing task details with an auto-generated ID.
*/
import jakarta.persistence.*;

// Notifies Spring that this class represents a table in the database
@Entity
public class Task {

    // Primary key of the table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}