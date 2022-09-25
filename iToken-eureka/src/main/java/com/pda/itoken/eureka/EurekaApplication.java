package com.pda.itoken.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册与发现中心
 */
@SpringBootApplication
@EnableEurekaServer// 启用注册发现服务器
public class EurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class,args);
	}
}
