package com.pda.itoken.web.posts.service.fallback;

import com.pda.itoken.common.hystrix.Fallback;
import com.pda.itoken.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

/**
 * @author PDA
 * @Date 2022/10/16 18:30
 * @Description 熔断器
 * @since version-1.0
 */
@Component
public class PostsServiceFallback implements PostsService {
	/**
	 * @author PDA
	 * @Date 2022/10/16 18:31
	 * @Description 分页熔断
	 * @Param [pageNum, pageSize, tbPostsPostJson]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String page(int pageNum, int pageSize, String tbPostsPostJson) {
		return Fallback.badGateway();
	}

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:39
	 * @Description id获取熔断
	 * @Param [postGuid]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String get(String postGuid) {
		return Fallback.badGateway();
	}

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:44
	 * @Description 新增熔断
	 * @Param [tbPostsPostJson, optsBy]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String save(String tbPostsPostJson, String optsBy) {
		return Fallback.badGateway();
	}
}
