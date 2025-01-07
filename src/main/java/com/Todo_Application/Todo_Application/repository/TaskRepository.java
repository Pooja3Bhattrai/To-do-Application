package com.Todo_Application.Todo_Application.repository;

import com.Todo_Application.Todo_Application.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// * Repository interface for Task entity operations
// * Extends JpaRepository to provide CRUD and JPA-specific operations
// *
// * @param <Task> The entity type this repository manages
// * @param <Integer> The type of the entity's primary key
// */

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

}
//Even though the interface appears empty, it inherits a rich set
// of database operations from JpaRepository, making it a powerful
// component in the data access layer without requiring additional code.