package com.antlukeliu.taskservice.domain.repository;

import com.antlukeliu.taskservice.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByCompleted(Pageable pageRequest, boolean completed);
    Page<Task> findAllByOrderByCompletedAsc(Pageable pageable);
    Page<Task> findAllByOrderByCompletedDesc(Pageable pageable);
    Page<Task> findByDescriptionContaining(Pageable pageable,String partialString);

}
