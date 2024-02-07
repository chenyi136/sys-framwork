package com.enterprise.base.system.repository.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;

@TableName("sys_menu")
@Data
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单(或者按钮等)类型
1:一级菜单;2:二级菜单;3:三级菜单;4:按钮;
     */
    @TableField("menu_type")
    private Integer menuType;
    /**
     * 上级菜单id
     */
    @TableField("menu_parent")
    private Integer menuParent;
    /**
     * 路径
     */
    @TableField("menu_path")
    private String menuPath;
    /**
     * 菜单资源状态 0:停用;1启用;
     */
    @TableField("menu_status")
    private Integer menuStatus;
    /**
     * 同级菜单显示顺序（从小到大）
     */
    @TableField("menu_order")
    private Integer menuOrder;

    /**
     * 菜单编号
     */
    @TableField("menu_index")
    private String menuIndex;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
