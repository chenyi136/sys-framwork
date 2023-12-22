package com.enterprise.biz.system.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enterprise.biz.system.repository.entity.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单资源 Mapper 接口
 * </p>
 *
 * @author zhangjx
 * @since 2020-10-12
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 根据用户id查询用户拥有权限的菜单
     * @param userId
     * @return
     */
    List<SysMenu> qryUserMenuByRoles(Long userId);

//    /**
//     * 获取菜单每个节点的子节点
//     * @return
//     */
//    List<MenuNodeChildRelDto> qryTreeNodeChildRel(Long userId);

}
