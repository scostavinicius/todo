package com.example.todo_list.services;

import com.example.todo_list.entities.Task;
import com.example.todo_list.exceptions.TaskNotFoundException;
import com.example.todo_list.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa com ID " + id + " não foi encontrada."));
    }

    // Obter todas as tasks de um usuário
    @Transactional(readOnly = true)
    public List<Task> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // Obter task específica de um usuário
    @Transactional(readOnly = true)
    public Task getTaskByUser(Long userId, Long taskId) {
        return taskRepository.findByUserIdAndId(userId, taskId)
                .orElseThrow(() -> new TaskNotFoundException("Tarefa com ID " + taskId + " não encontrada para o usuário com ID " + userId));
    }

//    @Transactional
//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    @Transactional
//    public Task updateTask(Long id, Task taskUpdate) {
//        Task task = getTaskById(id);
//
//        task.setTitle(taskUpdate.getTitle());
//        task.setDescription(taskUpdate.getDescription());
//        task.setCompleted(taskUpdate.getCompleted());
//
//        return taskRepository.save(task);
//    }
//
//    @Transactional
//    public void deleteTask(Long id) {
//        Task task = getTaskById(id);
//        taskRepository.delete(task);
//    }
}
