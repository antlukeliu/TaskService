package com.antlukeliu.taskservice.domain.repository;

import com.antlukeliu.taskservice.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
