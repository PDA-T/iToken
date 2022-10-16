package com.pda.itoken.web.posts.service;

import com.pda.itoken.common.web.service.BaseClientService;
import com.pda.itoken.web.posts.service.fallback.PostsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author PDA
 * @Date 2022/10/16 18:19
 * @Description This is description of interface
 * @since version-1.0
 */
@FeignClient(value = "iToken-service-posts",fallback = PostsServiceFallback.class)//绑定iToken-service-redis服务,熔断回调类
public interface PostsService extends BaseClientService {
	/**
	 * @author PDA
	 * @Date 2022/10/16 18:29
	 * @Description 分页查询
	 * @Param [pageNum, pageSize, tbPostsPostJson]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}",method = RequestMethod.GET)
	public String page(@PathVariable(required = true,value = "pageNum") int pageNum,
					   @PathVariable(required = true,value = "pageSize") int pageSize,
					   @PathVariable(required = false,value = "tbPostsPostJson") String tbPostsPostJson);

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:31
	 * @Description id获取文章
	 * @Param [postGuid]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/posts/{postGuid}",method = RequestMethod.GET)
	public String get(@PathVariable(required = true,value = "postGuid") String postGuid);

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:43
	 * @Description 保存文章
	 * @Param [tbPostsPostJson, optsBy]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "v1/posts",method = RequestMethod.POST)
	public String save(@PathVariable(required = true,value = "tbPostsPostJson") String tbPostsPostJson,
					   @PathVariable(required = true,value = "optsBy") String optsBy);
}
