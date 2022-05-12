package com.antlukeliu.taskservice.service;

import com.antlukeliu.taskservice.model.SortEnum;
import com.antlukeliu.taskservice.model.TaskDto;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<TaskDto> getTasks();
    Page<TaskDto> getSortedTasks(SortEnum sortBy, String partialSearchStr);
    boolean createTask(TaskDto taskDto);
    boolean updateTask(TaskDto taskDto);
    boolean deleteTask(Long taskId);
}
