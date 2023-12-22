package com.enterprise.manage.config;

import com.alibaba.fastjson.JSONObject;
import com.enterprise.framwork.interceptor.RequestInterceptor;
import com.enterprise.framwork.jwt.JwtProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 安全配置类
 */
@Order
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties({JwtProperties.class})
@Slf4j
public class SecureConfiguration implements WebMvcConfigurer {

	private final JwtProperties jwtProperties;

	@Autowired
	RequestInterceptor requestInterceptor;

    @Override
	public void addInterceptors(@NonNull InterceptorRegistry registry) {
		log.info("skip url ={}", JSONObject.toJSONString(jwtProperties.getIgnoreUrl()));
		registry.addInterceptor(requestInterceptor)
					.excludePathPatterns(jwtProperties.getIgnoreUrl());

	  }
}
