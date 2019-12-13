package com.fsd.service.task.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "TASK_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "TASK_ID")
    private Long taskId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
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
    private String status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID")
    private Project project;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;


}
