package com.enterprise.process.model;

import lombok.Data;

@Data
public class Process {
	String id;

	String deploymentId;

	String name;

	String resourceName;

	String key;

	String diagramresourceName;

	Integer version;

	Boolean suspended;

}
