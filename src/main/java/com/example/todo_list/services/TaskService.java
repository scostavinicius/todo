package com.example.todo_list.services;

import com.example.todo_list.dto.TaskDTO;
import com.example.todo_list.entities.Task;
import com.example.todo_list.entities.User;
import com.example.todo_list.exceptions.TaskNotFoundException;
import com.example.todo_list.exceptions.UserNotFoundException;
import com.example.todo_list.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
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
    public TaskDTO createTask(Long userId, TaskDTO taskDTO) {
        User user = userService.findUserById(userId);

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.getCompleted());
        task.setUser(user);

        task = taskRepository.save(task);

        return new TaskDTO(task);
    }

    @Transactional
    public TaskDTO updateTask(Long taskId, TaskDTO taskUpdate) {
        Task task = findTaskById(taskId);
        task.setTitle(taskUpdate.getTitle());
        task.setDescription(taskUpdate.getDescription());
        task.setCompleted(taskUpdate.getCompleted());

        return new TaskDTO(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = findTaskById(id);
        taskRepository.delete(task);
    }
}
