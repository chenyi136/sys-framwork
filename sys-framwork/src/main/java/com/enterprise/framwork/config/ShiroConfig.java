///**
// * MIT License
// * Copyright (c) 2018 yadong.zhang
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//package com.enterprise.framwork.config;
//
//import com.enterprise.framwork.shiro.UserRealm;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Shiro配置类
// */
//@Configuration
//public class ShiroConfig {
//
//    /**
//     *  Shiro自带的过滤器，可以在这里配置拦截页面
//     */
//    @Bean
//    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager securityManager){
//
//        // 1. 初始化一个ShiroFilter工程类
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        // 2. 我们知道Shiro是通过SecurityManager来管理整个认证和授权流程的，这个SecurityManager可以在下面初始化
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//        // 3. 上面我们讲过，有的页面不需登录任何人可以直接访问，有的需要登录才能访问，有的不仅要登录还需要相关权限才能访问
//        Map<String, String> filterMap = new LinkedHashMap<String, String>();
//
//        // 4. Shiro过滤器常用的有如下几种
//        // 4.1. anon 任何人都能访问，如登录页面
//        filterMap.put("/api/user/login", "anon");
////        filterMap.put("/api/user/test", "anon");
//        // 4.2. authc 需要登录才能访问
//        filterMap.put("/api/**", "authc");
//        // 5. 让ShiroFilter按这个规则拦截
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        // 6. 用户没登录被拦截后，当然需要调转到登录页了，这里配置登录页
//        shiroFilterFactoryBean.setLoginUrl("/api/user/login");
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * SecurityManager管理认证、授权整个流程
//     */
//    @Bean
//    public DefaultWebSecurityManager defaultWebSecurityManager(@Autowired UserRealm userRealm){
//
//        // 7. 新建一个SecurityManager供ShiroFilterFactoryBean使用
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        // 8. SecurityManager既然管理认证等信息，那他就需要一个类来帮他查数据库吧。这就是Realm类
//        securityManager.setRealm(userRealm);
//        return securityManager;
//    }
//
//}
