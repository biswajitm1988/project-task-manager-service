package com.fsd.service.task.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "TASK_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "TASK_ID")
    private Long taskId;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="PARENT_ID")
    private ParentTask parentTask;

    @Column(name = "TASK")
    private String taskSummary;

    @Column(name = "PRIORITY")
    private int priority;

    @OrderBy
    @Column(name = "START_DATE")
	@JsonFormat(locale = "en_US")
    private Date startDate;

    @Column(name = "END_DATE")
	@JsonFormat(locale = "en_US")
    private Date endDate;

    @Column(name = "TASK_STATUS")
    @JsonProperty("isTaskDone")
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")
    private Project project;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
}
