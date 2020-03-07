package com.example.shiro.config;

import com.example.shiro.authority.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:00
 * Shiro 核心配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * shiro 过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/common/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/unauth");
        /**
         * 1. anon：不需要认证的资源
         * 2. authc： 需要认证的资源
         * 3. user： 拥有的记住我的资源
         * 4. perms： 拥有对某个资源的权限
         * 5. roles：拥有某个角色
         */
        //定义过滤链集合
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/common/logout", "logout");
        filterChainDefinitionMap.put("/boo/login","anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 安全管理器
     * @param userRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return new DefaultWebSecurityManager();
    }
    /**
     * 用户登录Realm
     */
    @Bean
    public UserRealm userRealm()
    {
        UserRealm userRealm = new UserRealm();
//        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }
}
