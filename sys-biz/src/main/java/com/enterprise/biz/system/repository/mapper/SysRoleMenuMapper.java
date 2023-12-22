package com.enterprise.biz.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enterprise.biz.system.repository.entity.SysRoleMenu;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色和菜单(或者按钮等)关系 Mapper 接口
 * </p>
 *
 * @author zhangjx
 * @since 2020-10-12
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    List<String> selectAccessMenuIndex(@Param("roleId") Integer roleId);

    List<String>  selectAccessMenuId(@Param("roleId") Integer roleId);

    void insertRoleMenu(@Param("role_id") int role_id, @Param("menu_id")  int menu_id, @Param("create_time") Date create_time);

    void deleteRoleMenu(Integer id);
}
