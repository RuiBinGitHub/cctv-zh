package com.springboot.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.bean.MyRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

	@Resource
	private MyRealm myRealm;

	private SecurityManager manager = null;
	private ShiroFilterFactoryBean factoryBean = null;

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		manager = new DefaultWebSecurityManager(myRealm);
		factoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		factoryBean.setSecurityManager(manager);
		Map<String, String> filterMap = new HashMap<>();
		filterMap.put("/person/*", "roles[role2]");
		filterMap.put("/device/*", "roles[role2]");
		filterMap.put("/project/*", "authc");
		filterMap.put("/pipe/*", "authc");
		filterMap.put("/item/*", "authc");
		filterMap.put("/file/*", "authc");
		factoryBean.setFilterChainDefinitionMap(filterMap);
		// 配置跳转的登录页面
		factoryBean.setLoginUrl("/user/loginview");
		// 设置未授权提示页面
		factoryBean.setUnauthorizedUrl("/failure");
		return factoryBean;
	}

	/** 定义识图控制器 */
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/person/insertview").setViewName("person/insert");
		registry.addViewController("/device/insertview").setViewName("device/insert");
		/** 设置注册和登录跳转页面 */
		registry.addViewController("*/loginview").setViewName("user/login");
		registry.addViewController("*/logonview").setViewName("user/logon");
		/** 设置操作成功和失败跳转页面 */
		registry.addViewController("*/complete").setViewName("user/complete");
		registry.addViewController("*/success").setViewName("user/success");
		registry.addViewController("*/failure").setViewName("user/failure");
		registry.addViewController("/success").setViewName("user/success");
		registry.addViewController("/failure").setViewName("user/failure");
	}

	@Bean
	public ShiroDialect shiroDialect() {
		ShiroDialect dialect = new ShiroDialect();
		return dialect;
	}
}
