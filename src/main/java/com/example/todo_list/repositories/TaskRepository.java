package com.example.todo_list.repositories;

import com.example.todo_list.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Buscar tasks pelo ID do usuário
    List<Task> findByUserId(Long userId);

    // Buscar task específica de um usuário
    Optional<Task> findByUserIdAndId(Long userId, Long taskId);
}
