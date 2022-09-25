package com.pda.itoken.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务监控
 */
@SpringBootApplication
@EnableAdminServer// 启用服务监控(服务端)
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
public class AdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class,args);
	}
}
