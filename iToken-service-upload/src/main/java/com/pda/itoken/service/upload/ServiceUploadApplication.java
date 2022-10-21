package com.pda.itoken.service.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author PDA
 * @Date 2022/10/21 20:41
 * @Description 文件上传
 * @since version-1.0
 */
@SpringBootApplication
@EnableEurekaClient// 启用eureka客户端(服务注册进eureka服务器)
public class ServiceUploadApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceUploadApplication.class,args);
	}
}
