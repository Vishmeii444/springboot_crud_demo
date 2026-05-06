# Springboot Crud Application

Creating a simple RESTful API  with the use of Spring Boot to handle Task Management. This application ccontains a full CRUD lifecycle using a layered architecture and a SQL backend.

## Project Overview
This application serves as a backend service for managing a list of tasks. It handles incoming HTTP requests, processes business logic, and persists data in a MySQL database via phpMyAdmin.

---
## Project Structure
```
src/main/java/com/example/springboot_mysql_project/
│
├── SpringbootMysqlProjectApplication.java  # Starts the application
├── Task.java                               # Database table blueprint
├── TaskRepo.java                           # Handles SQL queries
└── TaskController.java                     # Handles Web requests
```

## Methods

| Method | URL | Description |
|---|---|---|
| GET | `/tasks` | Retrieve all tasks |
| POST | `/tasks` | Create a new task |
| GET | `/tasks/{id}` | Update an existing task |
| DELETE | `/tasks/{id}` | Delete a task by ID |
