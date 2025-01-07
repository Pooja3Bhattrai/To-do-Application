package com.Todo_Application.Todo_Application.services;

import com.Todo_Application.Todo_Application.entity.Task;
import com.Todo_Application.Todo_Application.enums.Status;
import com.Todo_Application.Todo_Application.exception.TaskNotFound;
import com.Todo_Application.Todo_Application.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
///**
// * Service class that handles business logic for Task operations
// */
@Service
public class TaskServices {
    @Autowired
//    /**
//     * Repository interface for Task entity operations
//     * @Autowired automatically injects the repository implementation
//     */
    TaskRepository repo;


//    /**
//     * Creates a new task in the system
//     * @param task The task object containing title, description, and status
//     * Creates a new Task object and copies properties from input task
//     * Saves the new task to the repository
//     */
    public void createTask(Task task) {
        Task all = new Task();
        all.setTitle(task.getTitle());
        all.setDescription(task.getDescription());
        all.setStatus(task.getStatus());

        this.repo.save(all);
    }
//    /**
//     * Retrieves a specific task by its ID
//     * @param id The unique identifier of the task
//     * @return Task object if found
//     * @throws TaskNotFound if no task exists with the given ID
//     */
    public Task getTaskById(Integer id) {
        return (Task)this.repo.findById(id).orElseThrow(() -> {
            return new TaskNotFound("Task not found jii");
        });
    }
//    /**
//     * Retrieves all tasks from the repository
//     * @return List of all tasks
//     * @throws TaskNotFound if no tasks exist in the system
//     */
    public List<Task> getAllTasks() throws TaskNotFound {
        List<Task> task = this.repo.findAll();
        if (task.isEmpty()) {
            throw new TaskNotFound("Ooops ! Task has no been added till now");
        } else {
            return task;
        }
    }
//    /**
//     * Updates an existing task with new information
//     * @param id The ID of the task to update
//     * @param updatedTask The task object containing updated information
//     * @return Updated task object
//     * @throws TaskNotFound if task doesn't exist or invalid status transition
//     *
//     * Implements status transition logic:
//     * - Cannot update an already completed task
//     * - PENDING can transition to INPROGRESS
//     * - INPROGRESS can transition to COMPLETED
//     */
public Task updateTask(Integer id, Task updatedTask) {
//first check and validate by using ID then if yes then we update otherwise our ciustomise excpetion will trhow
    Task task = repo.findById(id).orElseThrow(() -> new TaskNotFound("Oooops!  there is no task as such"));

    task.setTitle(updatedTask.getTitle()); //wanna update title
    task.setDescription(updatedTask.getDescription()); //wanna update description

//if user try to set completed but task has been already completed then we use this condition
    if (task.getStatus() == Status.COMPLETED && updatedTask.getStatus() == Status.COMPLETED) {
        throw new TaskNotFound(" hurrayy!!!Task has already been completed");
    }

//
    if (updatedTask.getStatus() == Status.INPROGRESS && task.getStatus() == Status.PENDING) {
        task.setStatus(Status.INPROGRESS);


    } else if (updatedTask.getStatus() == Status.COMPLETED && task.getStatus() == Status.INPROGRESS) {
        task.setStatus(Status.COMPLETED);
    } else {
        task.setStatus(updatedTask.getStatus());
    }


    return repo.save(task);
}
//    /**
//     * Deletes a task by its ID
//     * @param id The ID of the task to delete
//     * @return ResponseEntity with success/failure message and appropriate HTTP status
//     * Returns OK status if deletion successful
//     * Returns NOT_FOUND status if task doesn't exist
//     */
    public ResponseEntity<String> deleteTask(Integer id) {
        Optional<Task> task = this.repo.findById(id);
        if (task.isPresent()) {
            this.repo.deleteById(id);
            return new ResponseEntity("Delete hogya ji", HttpStatus.OK);
        } else {
            return new ResponseEntity("Ooops there is no such task to be deleted", HttpStatus.NOT_FOUND);
        }
    }
}



