package com.antlukeliu.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class TaskResponse {

    private Page<TaskDto> tasks;
}
