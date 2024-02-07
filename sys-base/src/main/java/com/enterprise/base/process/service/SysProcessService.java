package com.enterprise.base.process.service;

import com.enterprise.base.process.model.StartProcessContext;
import com.enterprise.common.model.response.PageInfo;
import com.enterprise.base.process.model.FlowInfo;
import java.util.List;

/**
 * @author chenyi
 */
public interface SysProcessService {

    /**
     * 查询所有正在运行的流程实例列表
     * @param businessKey
     * @param name
     * @param pageInfo
     * @return
     */
    List<FlowInfo> queryFlowList(String businessKey, String name, PageInfo pageInfo);

    /**
     * 删除流程
     * @param processKey
     * @param businessKey
     */
    void deleteProcess(String processKey, List<String> businessKey);


    /**
     * 发起一个流程,并完成第一个任务。关联业务表id
     * @param context
     */
    void startProcessByKey(StartProcessContext context);
}
