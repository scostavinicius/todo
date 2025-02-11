package com.example.todo_list.services;

import com.example.todo_list.dto.UserDTO;
import com.example.todo_list.entities.User;
import com.example.todo_list.exceptions.UserNotFoundException;
import com.example.todo_list.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método auxiliar
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não foi encontrado."));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserDTO(user)).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = findUserById(id);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        user = userRepository.save(user);

        return new UserDTO(user);
    }

    @Transactional
    public UserDTO updateUser(Long id, User userUpdate) {
        User user = findUserById(id);

        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());

        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }
}
