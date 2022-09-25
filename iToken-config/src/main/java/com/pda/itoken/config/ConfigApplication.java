package com.pda.itoken.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式配置中心
 */
@SpringBootApplication
@EnableConfigServer// 开启配置服务
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
public class ConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class,args);
	}
}
