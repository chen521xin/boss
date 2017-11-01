package com.boss.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Ace on 2017/5/27.
 */
//@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserApplication.class).web(true).run(args);    }
}
