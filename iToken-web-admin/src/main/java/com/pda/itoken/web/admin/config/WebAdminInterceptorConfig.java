package com.pda.itoken.web.admin.config;

import com.pda.itoken.web.admin.interceptor.WebAdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class WebAdminInterceptorConfig implements WebMvcConfigurer {

	/**
	 * 控制反转
	 * 防止拦截器自动注入null异常
	 * @return
	 */
	@Bean
	WebAdminInterceptor webAdminInterceptor(){
		return new WebAdminInterceptor();
	}

	/**
	 * 添加拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截路径,过滤静态文件
		registry.addInterceptor(webAdminInterceptor()).addPathPatterns("/**").excludePathPatterns("/static");
	}
}
