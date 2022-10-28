package com.pda.itoken.web.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author PDA
 * @Date 2022/10/28 18:58
 * @Description 后台
 * @since version-1.0
 */
@EnableDiscoveryClient// 服务消费者(服务注册进eureka服务器)
@EnableFeignClients// 启用Feign客户端
@SpringBootApplication(scanBasePackages = "com.pda.itoken")// 扫描包
public class WebBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebBackendApplication.class,args);
	}
}
