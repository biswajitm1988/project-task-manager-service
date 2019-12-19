package com.fsd.service.task.manager.rest.controller;

import com.fsd.service.task.manager.entity.ParentTask;
import com.fsd.service.task.manager.entity.Task;
import com.fsd.service.task.manager.service.TaskManagerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/task/manager")
public class TaskManagerController {

    @Autowired
    private TaskManagerService service;

    @ApiOperation(value = "Fetches all tasks from the database.", response = Task.class)
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks() throws Exception {
        log.info("TaskManagerController >> getAllTasks >> ");
        List<Task> tasks = service.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Fetch task by the provided id from the database.", response = Task.class)
    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable("id") Long id) throws Exception {
        log.info("TaskManagerController >> getTaskById >> "+id);
        Optional<Task> task = service.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the new task into the database and return new task.", response = Task.class)
    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) throws Exception {
        log.info("TaskManagerController >> addTask >> {}", task);
        Task newTask = service.saveTask(task);
        log.info("Task Added. New Id {} and task {}",newTask.getTaskId(),newTask);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update the new task into the database and return updated task", response = Task.class)
    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) throws Exception {
        log.info("TaskManagerController >> updateTask >> {}", task);
        Task updatedTask = service.saveTask(task);
        log.info("Task Updated. New Id {} and task {}",updatedTask.getTaskId(), updatedTask);
        return new ResponseEntity<>(updatedTask, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete Task. Test Purpose Only")
    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable("id") Long id) throws Exception {
        log.info("TaskManagerController >> deleteTask >> {}", id);
        service.deleteTaskById(id);
        log.info("Task Deleted.");
    }

    @ApiOperation(value = "Get All Parent Tasks")
    @GetMapping("/getAllParentTasks")
    public ResponseEntity<List<ParentTask>> getAllParentTasks() throws Exception {
        log.info("TaskManagerController >> getAllParentTasks >>");
        List<ParentTask> allParentTasks = service.getAllParentTasks();
        return new ResponseEntity<>(allParentTasks, HttpStatus.OK);
    }

    @ApiOperation(value = "Add the new parent task into the database and return new parent task.", response = ParentTask.class)
    @PostMapping("/addParentTask")
    public ResponseEntity<ParentTask> addParentTask(@RequestBody ParentTask parentTask) throws Exception {
        log.info("TaskManagerController >> addTask >> {}", parentTask);
        ParentTask newParentTask = service.saveParentTask(parentTask);
        log.info("Task Added. New Id {} and task {}",newParentTask.getParentId(), newParentTask);
        return new ResponseEntity<>(newParentTask, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetches all tasks from the database assigned to specific Project by id.", response = Task.class)
    @GetMapping("/getTasksByProjectId/{id}")
    public ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable("id") Long id) throws Exception {
        log.info("TaskManagerController >> getTasksByProjectId >> ");
        List<Task> tasks = service.getTasksByProjectId(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}