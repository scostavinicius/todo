package com.example.todo_list.dto;

import com.example.todo_list.entities.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Long userId;

    public TaskDTO(Task task) {
        BeanUtils.copyProperties(task, this);
        this.userId = (task.getUser() != null) ? task.getUser().getId() : null;
    }
}
