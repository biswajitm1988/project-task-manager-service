package com.fsd.service.task.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PROJECT_SEQ", initialValue = 1, allocationSize = 1)
    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "PROJECT_NAME")
    private String projectName;

    @Column(name = "PRIORITY")
    private int priority;

    @OrderBy
    @Column(name = "START_DATE")
	@JsonFormat(locale = "en_US")
    private Date startDate;

    @Column(name = "END_DATE")
	@JsonFormat(locale = "en_US")
    private Date endDate;

    @Column(name = "COMPLETION_STATUS")
    private String isProjectDone;

}
