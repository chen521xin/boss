/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boss.core.db.BaseEntity;
import com.boss.core.db.CaInfo;
import com.boss.core.struct.Result;
import com.boss.data.service.CaInfoService;
import com.boss.utils.feature.orm.mybatis.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @description
 * @data 2017年10月24日下午6:12:39
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Api("ca白名单")
@RestController
@RequestMapping("/caInfo")
public class CaInfoController {

	@Autowired
	private CaInfoService caInfoService;

	@ApiOperation(value = "ca白名单分页查询", httpMethod = "POST", response = Page.class, notes = "ca白名单分页查询")
	@PostMapping("/")
	public String getAllByPage(
			@ApiParam(required = false, name = "baseEntity", value = "ca入参vo") @RequestBody @Valid BaseEntity baseEntity,
			BindingResult result) {
		if(result.hasErrors()){
			Result.getValidationMessage(result);
		}
		Page<CaInfo> ca=new Page<>(baseEntity.getPageNo(),baseEntity.getPageSize());
		caInfoService.selectCaInfoListByPage(ca);
		return Result.successToJsonString(ca);
	}
}
