package com.enterprise.process.model;

import lombok.Data;

@Data
public class TaskInfo {
	
	private String taskId;
	
	private String businessKey;
	
	private String name;

	private String createTime;

	private String assignee;

}
