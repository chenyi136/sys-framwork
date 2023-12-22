package com.enterprise.biz.system.service;

import com.enterprise.biz.system.repository.entity.SysMenu;
import com.enterprise.biz.system.repository.entity.SysRole;
import com.enterprise.biz.system.repository.entity.SysUser;
import java.util.List;

/**
 * @author chenyi
 */
public interface UserService {
    void login(String userName, String pwd);

    void register();

    SysUser queryUserByName(String name);

    SysUser authenticate(String name, String password) throws Exception;

    List<SysMenu> queryUserMenu();

    List<SysRole>findRolesByUserId(Long userId);

    List<SysMenu>findPermissionByRoleId(Long roleId);
}
