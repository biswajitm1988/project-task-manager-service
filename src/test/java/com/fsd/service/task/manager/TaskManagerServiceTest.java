package com.fsd.service.task.manager;

import com.fsd.service.task.manager.entity.ParentTask;
import com.fsd.service.task.manager.entity.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskManagerServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/task/manager";
    }

    /**
     * Here we test that we can get all the Tasks in the database
     * using the GET method
     */
    @Test
    public void testGetAllTasks() {

        ResponseEntity<List> response = restTemplate.exchange(getRootUrl() + "/getAllTasks",
                HttpMethod.GET, new HttpEntity<String>(null, new HttpHeaders()), List.class);

        System.out.println(response.getBody().get(0));
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(!response.getBody().isEmpty());
    }

    /**
     * Here we test that we can fetch a single Task using its id
     */
    @Test
    public void testGetTaskById() {
        Long id =1l;
        Task task = restTemplate.getForObject(getRootUrl() + "/getTaskById/"+id, Task.class);

        System.out.println(task.getTaskSummary());
        Assert.assertNotNull(task);
    }

    /**
     * Here we test that we can create a task using the POST method
     */
    @Test
    public void testCreateAndDeleteTask() {
        Task task = new Task();
        ParentTask pt = new ParentTask();
        pt.setParentTaskSummary("Test Parent Task POST");
        task.setTaskSummary("Test Task POST");
        task.setPriority(9);
        task.setStartDate(new Date());
        task.setParentTask(pt);

        ResponseEntity<Task> postResponse = restTemplate.postForEntity(getRootUrl() + "/addTask", task, Task.class);
        Task newTask = postResponse.getBody();
        Assert.assertEquals(task.getTaskSummary(),newTask.getTaskSummary());
        Assert.assertEquals(task.getParentTask().getParentTaskSummary(),newTask.getParentTask().getParentTaskSummary());

        //Delete newly Created Task
        System.out.println("Calling Delete Task");
        restTemplate.delete(getRootUrl() + "/deleteTask/"+newTask.getTaskId());
        Task tempTask = restTemplate.getForObject(getRootUrl() + "/getTaskById/" + newTask.getTaskId(), Task.class);
        System.out.println("After Delete Task"+ tempTask);
        Assert.assertNull(tempTask);
    }

    /**
     * Here we test that we can update a task's information using the PUT method
     */
    @Test
    public void testUpdateTask() {
        Long id = 1l;
        Task task = restTemplate.getForObject(getRootUrl() + "/getTaskById/" + id, Task.class);
        Task tempTask = new Task();
        BeanUtils.copyProperties(task,tempTask);
        ParentTask pt = new ParentTask();
        pt.setParentTaskSummary("Test Parent Task PUT");
        task.setTaskSummary("Test Task PUT");
        task.setPriority(9);
        task.setStartDate(new Date());
        task.setParentTask(pt);

        restTemplate.put(getRootUrl() + "/updateTask", task);

        Task updatedTask = restTemplate.getForObject(getRootUrl() + "/getTaskById/" + id, Task.class);
        System.out.println(updatedTask.getTaskSummary());
        Assert.assertEquals(task.getTaskSummary(), updatedTask.getTaskSummary());

        //Put back Original Task
        System.out.println("Put Back Original Task");
        restTemplate.put(getRootUrl() + "/updateTask", tempTask);
        updatedTask = restTemplate.getForObject(getRootUrl() + "/getTaskById/" + id, Task.class);
        Assert.assertEquals(tempTask.getTaskSummary(), updatedTask.getTaskSummary());
    }
}
