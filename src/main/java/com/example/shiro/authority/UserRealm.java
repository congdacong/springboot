package com.example.shiro.authority;

import com.example.entity.SysUser;
import com.example.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:04
 * 自定义Realm，处理用户登录权限
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    SysUserService sysUserService;
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        System.out.println("授权");
       return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            SysUser sysUser = sysUserService.queryUserByName(userToken.getUsername());
            if(sysUser==null){
                return null;
            }
            //自定义盐值
            ByteSource salt = ByteSource.Util.bytes(sysUser.getUserName());

            /**
             *  1. principal：获取当前用户的认证
             *  2. password：密码
             *  3. realmName：认证名
             */
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), salt, getName());
            return info;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        System.out.println("清理缓存");
    }
}
