package com.fsd.service.task.manager.service;

import com.fsd.service.task.manager.entity.Task;
import com.fsd.service.task.manager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskManagerService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        log.info("Getting All Tasks from Database");
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        log.info("Saving the Task into Database");
        return repository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        log.info("Getting the Task by Id from Database for {}",id);
        return repository.findById(id);
    }

    public void deleteTask(Task task) {
        log.info("Deleting the Task from Database for {}",task.getTaskId());
        repository.delete(task);
    }

    public void deleteTaskById(Long id) {
        log.info("Deleting the Task from Database for {}",id);
        repository.deleteById(id);
    }
}
