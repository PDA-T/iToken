package com.pda.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
public class ServiceAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceAdminApplication.class,args);
	}
}
