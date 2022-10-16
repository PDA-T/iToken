package com.pda.itoken.common.web.service;

import com.pda.itoken.common.hystrix.Fallback;

/**
 * @author PDA
 * @Date 2022/10/16 18:22
 * @Description 通用服务消费者接口
 * @since version-1.0
 */
public interface BaseClientService {
	default String page(int pageNum,int pageSize,String domainJson){
		return Fallback.badGateway();
	}
}
