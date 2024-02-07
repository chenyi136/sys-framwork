package com.enterprise.base.system.user.dto;


import com.enterprise.base.system.post.db.SysPostEntity;
import com.enterprise.base.system.user.db.SysUserEntity;
import com.enterprise.base.system.role.db.SysRoleEntity;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserProfileDTO {

    public UserProfileDTO(SysUserEntity userEntity, SysPostEntity postEntity, SysRoleEntity roleEntity) {
        if (userEntity != null) {
            this.user = new UserDTO(userEntity);
        }

        if (postEntity != null) {
            this.postName = postEntity.getPostName();
        }

        if (roleEntity != null) {
            this.roleName = roleEntity.getRoleName();
        }
    }

    private UserDTO user;
    private String roleName;
    private String postName;

}
