package cc.sion.config;

import cc.sion.core.sys.biz.IUserAuthService;
import cc.sion.core.sys.security.shiro.SysUserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
    private static Map<String, Filter> LogoutFilterMap  = new LinkedHashMap<String, Filter>();


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
//        securityManager.setCacheManager(new org.apache.shiro.cache.ehcache.EhCacheManager());
        return securityManager;
    }


    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm shiroRealm() {
        SysUserRealm realm = new SysUserRealm();
        realm.setCredentialsMatcher(initCredentialsMatcher());
        return realm;
    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = null;

        matcher = new HashedCredentialsMatcher(IUserAuthService.HASH_ALGORITHM);
        matcher.setHashIterations(IUserAuthService.HASH_INTERATIONS);
        return matcher;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/public/**", "anon");
        //api
        filterChainDefinitionMap.put("/remote/**", "anon");

        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/sys/**", "authc,roles[sys]");
        filterChainDefinitionMap.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionMap.put("/**", "user");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/admin/wel");

        return shiroFilterFactoryBean;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager());
        return aasa;
    }

}
