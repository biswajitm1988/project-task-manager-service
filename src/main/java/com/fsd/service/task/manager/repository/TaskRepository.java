package com.fsd.service.task.manager.repository;

import com.fsd.service.task.manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.project.projectId=:projectId")
    List<Task> findAllTasksByProject(@Param("projectId") Long id);
}
