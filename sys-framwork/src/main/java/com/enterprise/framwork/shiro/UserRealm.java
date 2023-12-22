//package com.enterprise.framwork.shiro;
//
//import com.enterprise.biz.system.repository.entity.SysMenu;
//import com.enterprise.biz.system.repository.entity.SysRole;
//import com.enterprise.biz.system.repository.entity.SysUser;
//import com.enterprise.biz.system.service.UserService;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author chenyi
// */
//@Component
//public class UserRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UserService userService;
//
//    // 9. 前面被authc拦截后，需要认证，SecurityBean会调用这里进行认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("执行认证逻辑");
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        SysUser user = Optional.ofNullable(userService.queryUserByName(token.getUsername()))
//            .orElseThrow(() -> new UnknownAccountException("无此用户"));
//        //用用户名作为唯一盐值，确保序列化后的MD5值唯一
//        ByteSource salt = ByteSource.Util.bytes(user.getUserName());
//        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getUserPassword(), salt, getName());
//        return info;
//    }
//
//    // 10. 前面被roles拦截后，需要授权才能登录，SecurityManager需要调用这里进行权限查询
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行授权逻辑");
//        // 这里即使抛出runtime异常也会被shiro Advicefilter 捕获
//        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        List<SysRole> roleList = userService.findRolesByUserId(user.getUserId());
//        Optional.ofNullable(roleList).ifPresent(roles -> {
//            authorizationInfo.addRoles(roles.stream().map(SysRole::getRoleName).collect(Collectors.toList()));
//            List<String> permList = new ArrayList<>();
//            roles.forEach(role -> {
//                // 这里即使抛出runtime异常也会被shiro Advicefilter 捕获
//                List<SysMenu> permissions = userService.findPermissionByRoleId(role.getId());
//                Optional.ofNullable(permissions).ifPresent(perms -> {
//                    permList.addAll(perms.stream().map(SysMenu::getMenuName).collect(Collectors.toList()));
//                });
//            });
//            authorizationInfo.addStringPermissions(permList);
//        });
//        return authorizationInfo;
//    }
//}
