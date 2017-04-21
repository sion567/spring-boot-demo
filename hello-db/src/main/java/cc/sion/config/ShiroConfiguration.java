package cc.sion.config;

import cc.sion.core.sys.security.shiro.Md5PasswordServiceImpl;
import cc.sion.core.sys.security.shiro.SysUserRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.*;
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

    private static Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
    private static Map<String, Filter> LogoutFilterMap  = Maps.newLinkedHashMap();

    @Bean(name = "passwordService")
    public PasswordService passwordService(){
        return new DefaultPasswordService();
//        return new Md5PasswordServiceImpl();
    }
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher initCredentialsMatcher() {
        CredentialsMatcher matcher = new AllowAllCredentialsMatcher();
        matcher = new PasswordMatcher();
        return matcher;
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
//        securityManager.setCacheManager(new org.apache.shiro.cache.ehcache.EhCacheManager());
        securityManager.setCacheManager(new org.apache.shiro.cache.MemoryConstrainedCacheManager());
        return securityManager;
    }

    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthorizingRealm shiroRealm() {
        SysUserRealm realm = new SysUserRealm();
        realm.setCredentialsMatcher(initCredentialsMatcher());
        return realm;
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
//        filterChainDefinitionMap.put("/sys/**", "authc,roles[sys]");
//        filterChainDefinitionMap.put("/admin/**", "authc,roles[admin]");
        filterChainDefinitionMap.put("/**", "user");

        //注：anon，authcBasic，auchc，user是认证过滤器，
        //    perms，roles，ssl，rest，port是授权过滤器
        /**
         * anon:例子/admins/**=anon 没有参数，表示可以匿名使用。
         * authc表示需要认证(登录)才能使用，没有参数
         * roles：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
         * perms：例子/admins/user/**=perms[user:add:*],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         * rest：例子/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
         * port：例子/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
         * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证
         * ssl:例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
         * user:例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
         */



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
