package com.enterprise.biz.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.enterprise.biz.system.repository.entity.SysMenu;
import com.enterprise.biz.system.repository.entity.SysRole;
import com.enterprise.biz.system.repository.entity.SysRoleMenu;
import com.enterprise.biz.system.repository.entity.SysUser;
import com.enterprise.biz.system.repository.mapper.SysRoleMenuMapper;
import com.enterprise.biz.system.repository.mapper.SysUserRoleMapper;
import com.enterprise.biz.system.repository.mapper.UserMapper;
import com.enterprise.biz.system.service.UserService;
import com.enterprise.common.exception.ServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenyi
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public void login(String userName, String pwd) {

    }

    @Override
    public void register() {

    }




    public SysUser queryUserByName(String name) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, name);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public SysUser authenticate(String name, String password) throws Exception {
        SysUser sysUser = queryUserByName(name);
        if (sysUser == null) {
            throw new ServiceException("用户不存在");
        }
        if (!sysUser.getUserPassword().equals(password)) {
            throw new ServiceException("密码错误");
        }
        return sysUser;
    }

    public List<SysMenu> queryUserMenu() {
        return  null;
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public List<SysMenu> findPermissionByRoleId(Long roleId) {
        return null;
    }


}
