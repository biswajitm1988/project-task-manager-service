package com.fsd.service.task.manager.repository;

import com.fsd.service.task.manager.entity.ParentTask;
import com.fsd.service.task.manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {

}
