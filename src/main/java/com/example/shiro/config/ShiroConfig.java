package com.example.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.shiro.authority.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:congguangbo
 * @Date:2020/3/7 21:00
 * Shiro 核心配置类
 */

/**
 * 2020-03-21
 * 1.EhCacheManager:缓存的运用还需完善。
 * 2.filter:logout 登出应该添加filter。
 * 3.session: 会话的管理还需完善。
 */
@Configuration
public class ShiroConfig {
    /**
     * 用户登录Realm
     */
    @Bean
    public UserRealm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm);
        // 设置缓存
        securityManager.setCacheManager(getEhCacheManager());
        // 设置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    /**
     * shiro 过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/boo/index");
        /**
         * 没有权限返回的url
         */
        shiroFilterFactoryBean.setUnauthorizedUrl("/boo/unauth");
        /**
         * 1. anon：不需要认证的资源
         * 2. authc： 需要认证的资源,所有资源都登陆.
         * 3. user： 拥有的记住我的资源
         * 4. perms： 拥有对某个资源的权限,所有资源都要认证.
         * 5. roles：拥有某个角色
         */
        //定义过滤链集合
//        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/boo/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/image/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/boo/logout", "logout");
//        filterChainDefinitionMap.put("/**/**", "user");
        filterChainDefinitionMap.put("/**/**", "authc");
        // 所有请求需要认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;

    }

    /**
     * 加密方式
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        //此处的设置，true加密用的hex编码，false用的base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);

        return credentialsMatcher;
    }

    /**
     * thymeleaf模板引擎和shiro框架的整合
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 开启Shiro注解通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中-->安全管理器：securityManager可见securityManager是整个shiro的核心；
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    /**
     * 会话管理器
     */
    @Bean
    public DefaultWebSessionManager sessionManager()
    {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //自定义的SessionCookie
        manager.setSessionIdCookie(simpleCookie());
       //设置全局session超时时间
        manager.setGlobalSessionTimeout(30 * 60 * 1000); //毫秒
        manager.setSessionIdUrlRewritingEnabled(true);
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 取消url 后面的 JSESSIONID
        manager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
        manager.setSessionValidationScheduler(mySessionScheduler());
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
//        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
//        manager.setSessionFactory(sessionFactory());
        return manager;
    }


    @Bean
    public SessionValidationScheduler mySessionScheduler() {
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(100000); //毫秒
        return executorServiceSessionValidationScheduler;
    }
    /**
     * 设置cooke记住我
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
    /**
     * 设置session的Cookie
     */
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("JSESSIONIDZ");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }
    /**
     * cookie 属性设置
     */
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        cookie.setDomain("");
        /**
         * 多条cookie.setPath
         * 最后一条为准
         */
        cookie.setPath("/");
        cookie.setMaxAge(30 * 24 * 60 * 60); //秒
        return cookie;
    }
}
