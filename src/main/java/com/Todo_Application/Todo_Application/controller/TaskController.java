package com.Todo_Application.Todo_Application.controller;

import com.Todo_Application.Todo_Application.entity.Task;
import com.Todo_Application.Todo_Application.exception.TaskNotFound;
import com.Todo_Application.Todo_Application.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// * REST Controller for managing Task operations
// * This controller handles HTTP requests related to Task management
// */
@RestController
@RequestMapping({"/task"})
public class TaskController {

//    /**
//     * Autowired instance of TaskServices to handle business logic
//     */
    @Autowired
    TaskServices taskservices;




//    /**
//     * Creates a new task in the system
//     *
//     * @param task The Task object containing task details to be created
//     * @return String confirmation message
//     * @PreAuthorize Only users with ADMIN role can access this endpoint
//     */

    @PostMapping({"/create"})
    @PreAuthorize("hasRole('ADMIN')")
    public String createTask(@RequestBody Task task) {
        this.taskservices.createTask(task);
        return " ok";
    }

//    /**
//     * Retrieves a specific task by its ID
//     *
//     * @param id The unique identifier of the task
//     * Note: The implementation for this method appears to be incomplete in the current code
//     */
    @GetMapping({"/getTask/{id}"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
        try {
            Task task = this.taskservices.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (TaskNotFound e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping({"/getAll"})
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<Task> getAllTasks() {
        return this.taskservices.getAllTasks();
    }

//    /**
//     * Updates an existing task by its ID
//     * @PutMapping - Handles HTTP PUT requests to "/update/{id}"
//     * @PreAuthorize - Only users with 'ADMIN' role can access this endpoint
//     * @param id - The ID of the task to update
//     * @param updatedTask - The new task data to replace the existing task
//     * @return ResponseEntity<?> - Returns OK status with updated task if successful,
//     *         or BAD_REQUEST with error message if task not found
//     */
    @PutMapping({"/update/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTask(@PathVariable Integer id, @RequestBody Task updatedTask) {
        try {
            return ResponseEntity.ok(this.taskservices.updateTask(id, updatedTask));
        } catch (TaskNotFound e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    /**
//     * Deletes a task by its ID
//     * @DeleteMapping - Handles HTTP DELETE requests to "/delete/{id}"
//     * @PreAuthorize - Only users with 'ADMIN' role can access this endpoint
//     * @param id - The ID of the task to delete
//     * @return ResponseEntity<?> - Returns OK status if deletion successful,
//     *         or NOT_FOUND with error message if task doesn't exist
//     */
    @DeleteMapping({"/delete/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.taskservices.deleteTask(id));
        } catch (TaskNotFound e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}






