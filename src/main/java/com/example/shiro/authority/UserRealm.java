package com.example.shiro.authority;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:04
 * 自定义Realm，处理用户登录权限
 */
public class UserRealm extends AuthorizingRealm {
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
        String userName = "admin";
        String password = "123456";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        if(!userName.equals(userToken.getUsername())){
            return null;
        }
        userToken.getUsername();
        userToken.getPassword();
        System.out.println("登录认证");
        /**
         *  1. principal：获取当前用户的认证
         *  2. password：密码
         *  3. realmName：认证名
         */
        return new SimpleAuthenticationInfo("",password,"");
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        System.out.println("清理缓存");
    }
}
