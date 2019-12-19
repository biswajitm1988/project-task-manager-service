package com.fsd.service.task.manager.service;

import com.fsd.service.task.manager.entity.ParentTask;
import com.fsd.service.task.manager.entity.Task;
import com.fsd.service.task.manager.repository.ParentTaskRepository;
import com.fsd.service.task.manager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskManagerService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ParentTaskRepository parentTaskRepository;

    public List<Task> getAllTasks() {
        log.info("Getting All Tasks from Database");
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        log.info("Saving the Task into Database");
        if(task.getParentTask() != null){
            if(StringUtils.isEmpty(task.getParentTask().getParentTaskSummary())){
                task.setParentTask(null);
            }
        }
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

    public List<ParentTask> getAllParentTasks() {
        log.info("Getting All ParentTasks from Database");
        return parentTaskRepository.findAll();
    }

    public ParentTask saveParentTask(ParentTask parentTask) {
        log.info("Saving ParentTasks in Database");
        return parentTaskRepository.save(parentTask);
    }

    public List<Task> getTasksByProjectId(Long id) {
        log.info("Getting All Tasks from Database by Project Id");
        return repository.findAllTasksByProject(id);
    }
}
