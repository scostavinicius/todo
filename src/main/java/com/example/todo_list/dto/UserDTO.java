package com.example.todo_list.dto;

import com.example.todo_list.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;

    public UserDTO(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
