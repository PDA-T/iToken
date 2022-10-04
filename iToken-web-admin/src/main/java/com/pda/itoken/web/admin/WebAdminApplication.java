package com.pda.itoken.web.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 管理员服务 消费者
 */
@SpringBootApplication
@EnableDiscoveryClient// 服务消费者(服务注册进eureka服务器)
@EnableFeignClients// 启用Feign客户端
public class WebAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebAdminApplication.class,args);
	}
}
