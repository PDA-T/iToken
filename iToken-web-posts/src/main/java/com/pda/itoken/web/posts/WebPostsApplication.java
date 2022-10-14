package com.pda.itoken.web.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author PDA
 * @Date 2022/10/14 21:26
 * @Description 文档服务 消费者
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.itoken")// 设置包扫描路径
@EnableDiscoveryClient// 服务消费者(服务注册进eureka服务器)
@EnableFeignClients// 启用Feign客户端
public class WebPostsApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebPostsApplication.class,args);
	}
}
