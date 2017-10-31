/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import io.swagger.annotations.Api;

/**
 * @description
 * @data 2017年10月31日下午10:19:36
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("练习")
@RestController
@RequestMapping("/")
public class TestController {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private DiscoveryClient discoverClient;

	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("boss-service-data", false);
		return instance.getHomePageUrl();
	}
	
	@GetMapping("/eureka-info")
	public ServiceInstance serviceInfo() {
		ServiceInstance instance = this.discoverClient.getLocalServiceInstance();
		return instance;
	}
}
