package com.enterprise.base.system.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;


@TableName("sys_role")
@Data
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色可以访问的资源（id以逗号分隔）
     */
    @TableField("role_resource")
    private String roleResource;
    /**
     * 用户角色描述
     */
    @TableField("role_desc")
    private String roleDesc;
    /**
     * 角色状态 0:停用;1:启用;
     */
    @TableField("role_status")
    private Integer roleStatus;
    @TableField("update_time")
    private Date updateTime;
    /**
     * 预留字段（不一定会用）
     */
    @TableField("authority_name")
    private String authorityName;

}
