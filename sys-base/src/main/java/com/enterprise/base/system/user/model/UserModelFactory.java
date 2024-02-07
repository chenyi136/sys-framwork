package com.enterprise.base.system.user.model;

import com.enterprise.base.system.dept.model.DeptModelFactory;
import com.enterprise.base.system.post.model.PostModelFactory;
import com.enterprise.base.system.role.model.RoleModelFactory;
import com.enterprise.base.system.user.db.SysUserEntity;
import com.enterprise.base.system.user.db.SysUserService;
import com.enterprise.common.exception.ApiException;
import com.enterprise.common.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户模型工厂
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class UserModelFactory {

    private final SysUserService userService;

    private final PostModelFactory postModelFactory;

    private final DeptModelFactory deptModelFactory;

    private final RoleModelFactory roleModelFactory;

    public UserModel loadById(Long userId) {
        SysUserEntity byId = userService.getById(userId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, userId, "用户");
        }
        return new UserModel(byId, userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

    public UserModel create() {
        return new UserModel(userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

}
