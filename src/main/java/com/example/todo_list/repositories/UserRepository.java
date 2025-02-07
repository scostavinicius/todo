package com.example.todo_list.repositories;

import com.example.todo_list.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
