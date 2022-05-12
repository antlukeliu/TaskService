package com.antlukeliu.taskservice.service;

import com.antlukeliu.taskservice.domain.entity.Task;
import com.antlukeliu.taskservice.domain.repository.TaskRepository;
import com.antlukeliu.taskservice.model.SortEnum;
import com.antlukeliu.taskservice.model.TaskDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    private TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Page<TaskDto> getTasks() {
        return taskRepository
                .findAll(PageRequest.of(0, 20))
                .map(this::convertToDto);
    }

    @Override
    public Page<TaskDto> getSortedTasks(SortEnum field, String partialSearchStr) {

        return switch(field) {
            case COMPLETE_TRUE -> taskRepository.findAllByCompleted(PageRequest.of(0, 20), true).map(this::convertToDto);
            case COMPLETE_FALSE -> taskRepository.findAllByCompleted(PageRequest.of(0, 20), false).map(this::convertToDto);
            case COMPLETE_ASC -> taskRepository.findAllByOrderByCompletedAsc(PageRequest.of(0, 20)).map(this::convertToDto);
            case COMPLETE_DESC -> taskRepository.findAllByOrderByCompletedDesc(PageRequest.of(0, 20)).map(this::convertToDto);
            case DESCRIPTION -> taskRepository.findByDescriptionContaining(PageRequest.of(0, 20), partialSearchStr).map(this::convertToDto);
            default -> throw new IllegalArgumentException("Unknown sort criteria");
        };
    }

    @Override
    public boolean createTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean updateTask(TaskDto taskDto) {
        Task task = modelMapper.map(taskDto, Task.class);
        if(taskRepository.findById(task.getId()).isPresent()) {
          taskRepository.save(task);
          return true;
        } else {
            log.warn("Unable to find {}, did not update", taskDto.getId());
            return false;
        }
    }
    @Override
    public boolean deleteTask(Long taskId) {
        taskRepository
                .findById(taskId)
                .ifPresentOrElse(
                        taskRepository::delete,
                        () -> log.warn("unable to delete task ~ {} not found in DB ", taskId)
                );
        return true;
    }

    private TaskDto convertToDto(Task task) { return modelMapper.map(task, TaskDto.class); };
}
