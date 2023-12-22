package com.enterprise.biz.system.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;



@Data
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("user_password")
    private String userPassword;
    /**
     * 用户角色
     */
    @TableField("user_role")
    private String userRole;

    /**
     * 邮件地址
     */
    @TableField("mail_address")
    private String mailAddress;
    /**
     * 手机号
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 用户组织
     */
    private String organization;
    @TableField("create_time")
    private Date createTime;


}
