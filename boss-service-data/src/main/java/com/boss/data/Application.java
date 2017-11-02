package com.boss.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;



@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.boss.data","com.boss.spring","com.boss.db.config"})
@MapperScan("com.boss.data.mapper")
public class  Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

   
}
