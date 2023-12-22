package com.enterprise.process.service.impl;

import com.enterprise.common.model.PageInfo;
import com.enterprise.process.service.SysTaskService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenyi
 */
public class SysTaskServiceImpl implements SysTaskService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;


    @Override
    public List<Task> queryToCompleteTask(PageInfo pageInfo, String name, String assign) {
        TaskQuery condition = taskService.createTaskQuery();
        if (StringUtils.isNotEmpty(name)) {
            condition.taskName(name);
        }
        if (StringUtils.isNotEmpty(assign)) {
            condition.taskAssignee(assign);
        }
        int total = condition.active().orderByTaskCreateTime().desc().list().size();
        int start = (pageInfo.getCurrentPage()-1) * pageInfo.getPageSize();
        List<Task> taskList = condition.active().orderByTaskCreateTime().desc().listPage(start, pageInfo.getPageSize());
        return taskList;
    }

    @Override
    public List<Task> queryHistoryTask(String name, String assign) {
//        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
//        List<HistoricActivityInstance> history = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).activityType("userTask").orderByHistoricActivityInstanceStartTime().asc().list();
//        List<TaskInfo> infos  = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        history.stream().forEach(h->{
//            TaskInfo info = new TaskInfo();
//            info.setProcessInstanceId(h.getProcessInstanceId());
//            info.setStartTime(sdf.format(h.getStartTime()));
//            if (h.getEndTime() != null) {
//                info.setEndTime(sdf.format(h.getEndTime()));
//            }
//            info.setAssignee(h.getAssignee());
//            info.setTaskName(h.getActivityName());
//            List<Comment> comments = taskService.getTaskComments(h.getTaskId());
//            if (comments.size() > 0) {
//                info.setComment(comments.get(0).getFullMessage());
//            }
//            infos.add(info);
//        });
//        return infos;
        return null;
    }
}
