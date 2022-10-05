package com.pda.itoken.service.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 单点登陆
 */
@SpringBootApplication(scanBasePackages = "com.pda.itoken")// 设置包扫描路径
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
@EnableDiscoveryClient// 服务消费者(服务注册进eureka服务器)
@EnableFeignClients// 启用Feign客户端
@MapperScan(basePackages = "com.pda.itoken.service.sso.mapper")// 要扫描的Mapper类的包路径
public class ServiceSSOApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceSSOApplication.class,args);
	}
}
