package com.pda.itoken.service.upload.fastdfs;

import org.springframework.context.annotation.Bean;

/**
 * @author PDA
 * @Date 2022/10/21 21:16
 * @Description Java 配置方式定义 StorageFactory 的 Bean 使其可以被依赖注入
 * @since version-1.0
 */
public class FastDFSConfiguration {
	@Bean
	public StorageFactory storageFactory() {
		return new StorageFactory();
	}
}
