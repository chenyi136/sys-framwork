package com.enterprise.base.process.service;

import com.enterprise.common.model.response.PageInfo;
import com.enterprise.base.process.model.TaskInfo;
import java.util.List;
import org.activiti.engine.task.Task;

/**
 * @author chenyi
 */
public interface SysTaskService {
    //查询待办任务
    List<Task> queryToCompleteTask(PageInfo pageInfo, String name, String assign);
    //查询任务办理时间轴
    List<TaskInfo> queryHistoryTask(String taskId);
    // 任务审批
    void complete(String taskId);
}
