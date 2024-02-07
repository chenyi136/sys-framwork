package com.enterprise.base.system.dept.model;

import com.enterprise.base.system.dept.db.SysDeptService;
import com.enterprise.base.system.dept.command.AddDeptCommand;
import com.enterprise.base.system.dept.db.SysDeptEntity;
import com.enterprise.common.exception.ApiException;
import com.enterprise.common.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class DeptModelFactory {

    private final SysDeptService deptService;

    public DeptModel loadById(Long deptId) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, deptId, "部门");
        }
        return new DeptModel(byId, deptService);
    }

    public DeptModel create() {
        return new DeptModel(deptService);
    }

    public DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
        model.setParentId(addCommand.getParentId());
        model.setDeptName(addCommand.getDeptName());
        model.setOrderNum(addCommand.getOrderNum());
        model.setLeaderName(addCommand.getLeaderName());
        model.setPhone(addCommand.getPhone());
        model.setEmail(addCommand.getEmail());
        return model;
    }



}
