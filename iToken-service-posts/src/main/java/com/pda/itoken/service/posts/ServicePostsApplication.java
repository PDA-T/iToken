package com.pda.itoken.service.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author PDA
 * @Date 2022/10/9 18:18
 * @Description 文章服务 提供者
 * @since version-1.0
 */
@SpringBootApplication(scanBasePackages = "com.pda.itoken")// 扫描全部包
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
@MapperScan(basePackages = {"com.pda.itoken.common.mapper","com.pda.itoken.service.posts.mapper"})// 要扫描的Mapper类的包路径
public class ServicePostsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicePostsApplication.class,args);
	}
}
