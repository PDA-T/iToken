package com.pda.itoken.web.backend.config;

import com.pda.itoken.web.backend.interceptor.WebBackendInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class WebBackendInterceptorConfig implements WebMvcConfigurer {

	/**
	 * 控制反转
	 * 防止拦截器自动注入null异常
	 * @return
	 */
	@Bean
	WebBackendInterceptor webBackendInterceptor(){
		return new WebBackendInterceptor();
	}

	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截路径,过滤静态文件
		registry.addInterceptor(webBackendInterceptor()).addPathPatterns("/**").excludePathPatterns("/static");
	}
}
