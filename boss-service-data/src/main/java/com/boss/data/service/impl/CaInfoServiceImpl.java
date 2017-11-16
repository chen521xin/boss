/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boss.core.db.CaInfo;
import com.boss.data.mapper.CaInfoMapper;
import com.boss.data.service.CaInfoService;
import com.boss.db.annotation.ReadOnlyConnection;
import com.boss.db.feature.orm.mybatis.Page;

/**
 * @description
 * @data 2017年10月24日下午6:13:37
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Service
public class CaInfoServiceImpl implements CaInfoService {

	@Autowired
	private CaInfoMapper cainfoMapper;

	@ReadOnlyConnection
	@Override
	public Page<CaInfo> selectCaInfoListByPage(Page<CaInfo> caInfo) {
		cainfoMapper.selectCaInfoListByPage(caInfo);
		return caInfo;
	}

}
