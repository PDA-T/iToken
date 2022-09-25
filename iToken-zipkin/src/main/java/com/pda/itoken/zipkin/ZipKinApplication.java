package com.pda.itoken.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.internal.EnableZipkinServer;

/**
 * 服务链路追踪
 */
@SpringBootApplication
@EnableZipkinServer// 启用服务链路追踪
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
public class ZipKinApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZipKinApplication.class,args);
	}
}
