package com.fsd.service.task.manager.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "PARENT_TASKS")
public class ParentTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PARENT_TASK_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "PARENT_TASK", unique = true)
    private String parentTaskSummary;

}
