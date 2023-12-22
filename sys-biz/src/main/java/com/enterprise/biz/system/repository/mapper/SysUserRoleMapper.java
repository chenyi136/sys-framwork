package com.enterprise.biz.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enterprise.biz.system.repository.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 用户和角色关系 Mapper 接口
 * </p>
 *
 * @author zhangjx
 * @since 2020-10-12
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    Integer selectRoleIdByUserId(@Param("userId") Long userId);

    void insertUserRole(@Param("user_id") Long user_id, @Param("role_id")  int role_id);

    void deleteUserRole(@Param("user_id")  Long user_id);
}
