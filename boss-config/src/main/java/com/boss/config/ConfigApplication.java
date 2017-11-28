package com.boss.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ${DESCRIPTION}
 *
 * @author 
 * @create 2017-05-25 12:44
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient // 启动一个服务注册中心提供给其他应用进行对话
public class ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
		System.out.println("==================配置中心启动=====================");
	}
}
