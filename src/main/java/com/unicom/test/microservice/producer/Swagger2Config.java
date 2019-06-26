package com.unicom.test.microservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger配置类
 * 
 * @author huangjl,lixiaofeng
 *
 */
@Configuration
public class Swagger2Config {

	private static final Logger LOG = LoggerFactory.getLogger(Swagger2Config.class);

	@Value("${swagger.apiTagFile:swaggerApiTags.properties}")
	String apiTagConfigPath = "swaggerApiTags.properties";

	/**
	 * 
	 * 配置swagger
	 * @return Docket docket
	 */
	@Bean
	public Docket createRestApi() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				//.tags(new Tag("DevOps", "DevOps 前端API列表"), getTags())
				.select()
				// 扫描指定包中的swagger注解
				// .apis(RequestHandlerSelectors.basePackage("com.xia.controller"))
				// 扫描所有有注解的api，用这种方式更灵活
				// .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.apis(RequestHandlerSelectors.basePackage("com.unicom.test.microservice"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * 
	 * 配置apiInfo信息
	 * @return ApiInfo swagger配置信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("联通微服务 APIs").description("基础平台 RESTful 风格的接口文档")
				.termsOfServiceUrl("http://mcloud.chinaunicom.cn/").version("1.0").build();
	}
}
