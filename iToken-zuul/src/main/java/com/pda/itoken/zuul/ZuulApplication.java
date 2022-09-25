package com.pda.itoken.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul路由网关
 * 服务过滤
 */
@SpringBootApplication
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
@EnableZuulProxy// 启用Zuul路由代理
public class ZuulApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}
}
