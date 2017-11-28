/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boss.api.client.impl.CaInfoServiceClientImpl;

/**
 * @description feign调用方法，feign已实现负载均衡。
 * @data 2017年11月28日下午5:16:28
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/

@FeignClient(name="boss-service-data"+"${PRODUCER_TAG}",fallback=CaInfoServiceClientImpl.class)
public interface CaInfoServiceClient {

	@RequestMapping(method=RequestMethod.POST,value="/caInfo/",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody String getAllByPage(@RequestParam("id") String id,@RequestParam("username") String username);
}
