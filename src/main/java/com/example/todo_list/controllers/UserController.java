package com.example.todo_list.controllers;

import com.example.todo_list.entities.Task;
import com.example.todo_list.entities.User;
import com.example.todo_list.services.TaskService;
import com.example.todo_list.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    // Buscar todos os usuários
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Buscar um usuário pelo id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Obter tasks de um usuário específico
    @GetMapping("/{userId}/tasks")
    public List<Task> getTasksByUser(@PathVariable Long userId) {
        userService.getUserById(userId); // Lança exceção se o usuário não existir
        return taskService.getTasksByUser(userId);
    }

    // Obter task específica de um usuário
    @GetMapping("/{userId}/tasks/{taskId}")
    public Task getTaskByUser(@PathVariable Long userId, @PathVariable Long taskId) {
        userService.getUserById(userId); // Lança exceção se o usuário não existir
        return taskService.getTaskByUser(userId, taskId);
    }

    // Criar um novo usuário
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Atualizar um usuário
    @PostMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
