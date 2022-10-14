package com.pda.itoken.service.posts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author PDA
 * @Date 2022/10/13 20:07
 * @Description 文档引擎配置
 * @since version-1.0
 */
@Configuration
public class Swagger2Config {

	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pda.itoken.service.posts.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("iToken API 文档")
				.description("iToken API 网关接口:http://1lin.xyz")
				.termsOfServiceUrl("http://1lin.xyz")
				.version("1.0.0")
				.build();
	}
}
