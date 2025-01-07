package com.Todo_Application.Todo_Application.exception;




// * Custom exception class for handling task-related errors
// * Extends RuntimeException to represent unchecked exceptions that occur during task operations
// */
public class TaskNotFound extends RuntimeException{


// * Constructor for TaskNotFound exception
// *
// * @param message The detailed error message describing why the task was not found
// * Calls the parent RuntimeException constructor with the provided message
// *

public TaskNotFound(String message) {
        super(message);
    }
}
