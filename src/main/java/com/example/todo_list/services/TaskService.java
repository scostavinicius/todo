package com.example.todo_list.services;

import com.example.todo_list.dto.TaskDTO;
import com.example.todo_list.entities.Task;
import com.example.todo_list.exceptions.TaskNotFoundException;
import com.example.todo_list.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Método auxiliar
    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa com ID " + id + " não foi encontrada."));
    }

    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(task -> new TaskDTO(task)).toList();
    }

    @Transactional(readOnly = true)
    public TaskDTO getTaskById(Long id) {
        Task task = findTaskById(id);
        return new TaskDTO(task);
    }

    // Obter todas as tasks de um usuário
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByUser(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);
        return tasks.stream().map(task -> new TaskDTO(task)).toList();
    }

    // Obter task específica de um usuário
    @Transactional(readOnly = true)
    public TaskDTO getTaskByUser(Long userId, Long taskId) {
        Task task = taskRepository.findByUserIdAndId(userId, taskId)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa com ID " + taskId + " não encontrada para o usuário com ID " + userId));

        return new TaskDTO(task);
    }

    @Transactional
    public TaskDTO createTask(Task task) {
        Task createdTask = taskRepository.save(task);
        return new TaskDTO(createdTask);
    }

    @Transactional
    public Task updateTask(Long id, Task taskUpdate) {
        Task task = findTaskById(id);

        task.setTitle(taskUpdate.getTitle());
        task.setDescription(taskUpdate.getDescription());
        task.setCompleted(taskUpdate.getCompleted());

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = findTaskById(id);
        taskRepository.delete(task);
    }
}
