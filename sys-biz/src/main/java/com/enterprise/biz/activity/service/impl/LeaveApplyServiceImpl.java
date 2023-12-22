package com.enterprise.biz.activity.service.impl;

import com.enterprise.biz.activity.service.LeaveApplyService;
import org.springframework.stereotype.Service;

/**
 * @author chenyi
 */
@Service
public class LeaveApplyServiceImpl implements LeaveApplyService {
//    @Autowired
//    private RuntimeService runtimeService;
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private LeaveApplyMapper leaveApplyMapper;

//    @Override
//    public void apply(LeaveApply leaveApply) {
//        //1.请假业务表入库
//        leaveApplyMapper.insert(leaveApply);
//        // 2.发起请假流程
//        HashMap<String, Object> variables = new HashMap<>();
//        variables.put("applyUserId", leaveApply.getUserId());
//        variables.put("departLeader", leaveApply.getDeptleader());
//        // 关联业务表id、审批人
//        runtimeService.startProcessInstanceByKey("leave", String.valueOf(leaveApply.getId()), variables);
//        // 3.自动完成第一个任务
//        Task autoTask = taskService.createTaskQuery().processDefinitionKey("leave")
//            .processInstanceBusinessKey(String.valueOf(leaveApply.getId())).singleResult();
//        taskService.complete(autoTask.getId());
//    }
}
