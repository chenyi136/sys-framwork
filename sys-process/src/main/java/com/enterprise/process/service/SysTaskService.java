package com.enterprise.process.service;

import com.enterprise.common.model.PageInfo;
import java.util.List;
import org.activiti.engine.task.Task;

/**
 * @author chenyi
 */
public interface SysTaskService {
    //查询待办任务
    List<Task> queryToCompleteTask(PageInfo pageInfo, String name, String assign);
    //查询历史任务
    List<Task> queryHistoryTask(String name, String assign);
}
