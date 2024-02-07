package com.enterprise.base.process.service.impl;

import com.enterprise.base.process.model.StartProcessContext;
import com.enterprise.base.process.service.SysProcessService;
import com.enterprise.common.model.response.PageInfo;
import com.enterprise.base.process.model.FlowInfo;
import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyi
 */
@Service
public class SysProcessServiceImpl implements SysProcessService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;

    @Override
    public List<FlowInfo> queryFlowList(String bussinesskey, String name, PageInfo pageInfo) {
        int start = (pageInfo.getCurrentPage() - 1) * pageInfo.getPageSize();
        ProcessInstanceQuery condition = runtimeService.createProcessInstanceQuery();
        if (StringUtils.isNotEmpty(bussinesskey)) {
            condition.processInstanceBusinessKey(bussinesskey);
        }
        if (StringUtils.isNotEmpty(name)) {
            condition.processDefinitionName(name);
        }
//        int total = condition.orderByProcessDefinitionId().desc().list().size();
        List<ProcessInstance> processList = condition.orderByProcessDefinitionId().desc()
            .listPage(start, pageInfo.getPageSize());
        List<FlowInfo> flows = new ArrayList<>();
        processList.stream().forEach(p -> {
            FlowInfo info = new FlowInfo();
            info.setProcessInstanceId(p.getProcessInstanceId());
            info.setBusinessKey(p.getBusinessKey());
            info.setName(p.getProcessDefinitionName());
            info.setStartTime(p.getStartTime());
            info.setStartUserId(p.getStartUserId());
            info.setSuspended(p.isSuspended());
            info.setEnded(p.isEnded());
            // 查看当前活动任务
            List<Task> tasks =  taskService.createTaskQuery().processInstanceId(p.getProcessInstanceId()).list();
            if (tasks.size() > 0) {
                String taskName = "";
                String assignee = "";
                for (Task t : tasks) {
                    taskName += t.getName() + ",";
                    assignee += t.getAssignee() + ",";
                }
                taskName = taskName.substring(0, taskName.length() -1);
                assignee = assignee.substring(0, assignee.length() -1);
                info.setCurrentTask(taskName);
                info.setAssignee(assignee);
            }
            flows.add(info);
        });
       return flows;
    }

    @Override
    public void deleteProcess(String processKey ,List<String> businessKeys) {
        for (String key : businessKeys) {
            ProcessInstance process = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processKey).processInstanceBusinessKey(key).singleResult();
            if (process != null) {
                runtimeService.deleteProcessInstance(process.getId(), "删除");
            }
            // 删除历史数据
            HistoricProcessInstance history = historyService.createHistoricProcessInstanceQuery()
                .processDefinitionKey("leave").processInstanceBusinessKey(key).singleResult();
            if (history != null) {
                historyService.deleteHistoricProcessInstance(history.getId());
            }
        }
    }

    @Override
    public void startProcessByKey(StartProcessContext context) {
        //1.设置流程启动人 （通过此id获取发起人的流程实例记录,统计申请记录次数）
        identityService.setAuthenticatedUserId(context.getAuthenticator());
        runtimeService.startProcessInstanceByKey(context.getProcessKey(), context.getBusinessKey(), context.getVariables());
        // 自动完成第一个任务
        Task autoTask = taskService.createTaskQuery()
            .processDefinitionKey(context.getProcessKey())
            .processInstanceBusinessKey(context.getBusinessKey())
            .singleResult();
        taskService.complete(autoTask.getId());
    }
}
