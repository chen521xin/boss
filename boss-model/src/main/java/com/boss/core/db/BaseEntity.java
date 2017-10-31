/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.core.db;

import java.io.Serializable;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description
 * @data 2017年10月30日下午3:58:32
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6183206662169197736L;
	/**
	 * 页码
	 */
	@Min(value=1,message="最小值为1")
	@ApiModelProperty(value = "最后修改人", dataType = "java.lang.String", required = false)
	private int pageNo=1;
	/**
	 * 页码大小
	 */
	@Min(value=1,message="最小值为1")
	@ApiModelProperty(value = "最后修改人", dataType = "java.lang.String", required = false)
	private int pageSize=10;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
