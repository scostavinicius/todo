package com.example.todo_list.controllers;

import com.example.todo_list.dto.TaskDTO;
import com.example.todo_list.dto.UserDTO;
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
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Buscar um usuário pelo id
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Obter tasks de um usuário específico
    @GetMapping("/{userId}/tasks")
    public List<TaskDTO> getTasksByUser(@PathVariable Long userId) {
        userService.findUserById(userId); // Lança exceção se o usuário não existir
        return taskService.getTasksByUser(userId);
    }

    // Obter task específica de um usuário
    @GetMapping("/{userId}/tasks/{taskId}")
    public TaskDTO getTaskByUser(@PathVariable Long userId, @PathVariable Long taskId) {
        userService.findUserById(userId); // Lança exceção se o usuário não existir
        return taskService.getTaskByUser(userId, taskId);
    }

    // Criar um novo usuário
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // Atualizar um usuário
    @PostMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    // Adiciona uma task para um usuário
    @PostMapping("/{userId}/tasks")
    public TaskDTO createTask(@PathVariable Long userId,@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(userId, taskDTO);
    }

    // Deletar um usuário
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
