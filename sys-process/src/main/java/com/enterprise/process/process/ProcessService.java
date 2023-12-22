package com.enterprise.process.process;

import java.io.OutputStream;
import java.util.List;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.TaskInfo;

/**
 * @author chenyi
 */
public interface ProcessService {
    // 流程跟踪进度
    void generateFlowChart(String processInstanceId, OutputStream outputStream);

    // 流程任务审批
    List<TaskInfo> queryTask(String param);

    // 任务审批
    void completeTask(String taskId);

    // 历史任务
    List<TaskInfo> history(String param);
}
