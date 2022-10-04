package com.pda.itoken.common.hystrix;

import com.google.common.collect.Lists;
import com.pda.itoken.common.constants.HttpStatusConstants;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.common.utils.MapperUtils;

/**
 * 通用熔断方法
 */
public class Fallback {
	/**
	 * 502错误
	 * @return
	 */
	public static String badGateway(){
		BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(
				new BaseResult.Error(
						String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
						HttpStatusConstants.BAD_GATEWAY.getContent()
				)
		));
		try {
			return MapperUtils.obj2json(baseResult);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}