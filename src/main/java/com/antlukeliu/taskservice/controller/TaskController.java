package com.antlukeliu.taskservice.controller;

import com.antlukeliu.taskservice.model.SortEnum;
import com.antlukeliu.taskservice.model.TaskRequest;
import com.antlukeliu.taskservice.model.TaskResponse;
import com.antlukeliu.taskservice.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="task")
public class TaskController {

    private TaskService taskService;

    private TaskController(TaskService taskService) { this.taskService = taskService; }

    @GetMapping
    public ResponseEntity<TaskResponse> getTasks() {
        return new ResponseEntity<>(new TaskResponse(taskService.getTasks()), HttpStatus.OK);
    }

    @GetMapping(value="/sort")
    public ResponseEntity<TaskResponse> getSortedTask(@RequestParam SortEnum field, @RequestParam(required = false) String partialSearchStr) {
        return new ResponseEntity<>(new TaskResponse(taskService.getSortedTasks(field, partialSearchStr)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> createTask(@RequestBody TaskRequest taskRequest) {
        return new ResponseEntity<>(taskService.createTask(taskRequest.getTaskDto()), HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody TaskRequest taskRequest) {
        if(taskRequest.getTaskDto().getId() == null) throw new NullPointerException("Id cannot be null to update task!");
        if (taskService.updateTask(taskRequest.getTaskDto())) {
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        } else return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping(value="/{taskId}")
    public ResponseEntity<Boolean> createTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.ACCEPTED);
    }
}
