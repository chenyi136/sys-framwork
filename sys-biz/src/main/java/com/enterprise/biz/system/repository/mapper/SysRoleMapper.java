package com.enterprise.biz.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enterprise.biz.system.repository.entity.SysRole;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author zhangjx
 * @since 2020-10-12
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户id查询角色
     * @param userId
     * @return
     */
    List<Integer> qryUserRoles(Long userId);


    List<SysRole> getRoles(@Param("role_name") String role_name,
                  @Param("update_time") Date update_time,
                  @Param("start_time") String start_time,
                  @Param("end_time") String end_time,
                  @Param("batch_size") int batch_size,
                  @Param("page_number") int page_number,
                  @Param("role_status") int role_status,
                  @Param("role_desc") String role_desc,
                  @Param("authority_name") String authority_name);

    int getRolesCount(@Param("role_name") String role_name,
                      @Param("update_time") Date update_time,
                      @Param("start_time") String start_time,
                      @Param("end_time") String end_time,
                      @Param("batch_size") int batch_size,
                      @Param("page_number") int page_number,
                      @Param("role_status") int role_status,
                      @Param("role_desc") String role_desc,
                      @Param("authority_name") String authority_name);

    SysRole getRolesByName(@Param("role_name") String role_name);

    int insertRoles(@Param("roleName") String roleName,
                     @Param("roleResource") String roleResource,
                     @Param("roleDesc") String roleDesc,
                     @Param("roleStatus") Integer roleStatus,
                     @Param("updateTime") Date updateTime,
                     @Param("authorityName") String authorityName);

    void deleteRoles(@Param("role_name") String role_name);

    void editRoles(@Param("roleName") String roleName,
                   @Param("roleResource") String roleResource,
                   @Param("roleDesc") String roleDesc,
                   @Param("roleStatus") Integer roleStatus,
                   @Param("updateTime") Date updateTime,
                   @Param("authorityName") String authorityName,
                   @Param("id") Integer id);

    SysRole getRolesById(@Param("id") String id);
}
