package com.pda.itoken.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 管理员服务 提供者
 */
@SpringBootApplication
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
@MapperScan(basePackages = "com.pda.itoken.service.admin.mapper")// 要扫描的Mapper类的包路径
public class ServiceAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceAdminApplication.class,args);
	}
}
