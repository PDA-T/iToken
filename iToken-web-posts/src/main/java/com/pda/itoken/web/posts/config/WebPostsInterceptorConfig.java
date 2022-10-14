package com.pda.itoken.web.posts.config;

import com.pda.itoken.web.posts.interceptor.WebPostsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 */
@Configuration
public class WebPostsInterceptorConfig implements WebMvcConfigurer {

	/**
	 * 控制反转
	 * 防止拦截器自动注入null异常
	 * @return
	 */
	@Bean
	WebPostsInterceptor webAdminInterceptor(){
		return new WebPostsInterceptor();
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
