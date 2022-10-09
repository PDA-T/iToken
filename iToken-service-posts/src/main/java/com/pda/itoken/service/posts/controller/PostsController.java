package com.pda.itoken.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.pda.itoken.common.domain.TbPostsPost;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.service.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PDA
 * @Date 2022/10/9 18:32
 * @Description 文章Controller
 * @since version-1.0
 */
@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {
	// 文章
	@Autowired
	private PostsService<TbPostsPost> postsService;

	/**
	 * @author PDA
	 * @Date 2022/10/9 18:39
	 * @Description 根据id获取文章
	 * @Param [postGuid]
	 * @return com.pda.itoken.common.dto.BaseResult
	 * @since version-1.0
	 */
	@RequestMapping(value = "{postGuid}",method = RequestMethod.GET)
	public BaseResult get(@PathVariable("postGuid") String postGuid){
		TbPostsPost tbPostsPost = new TbPostsPost();
		tbPostsPost.setPostGuid(postGuid);
		TbPostsPost obj = postsService.selectOne(tbPostsPost);
		return BaseResult.ok(obj);
	}

	/**
	 * @author PDA
	 * @Date 2022/10/9 18:50
	 * @Description 保存文章
	 * @Param [tbPostsPostJson, optsBy]
	 * @return com.pda.itoken.common.dto.BaseResult
	 * @since version-1.0
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BaseResult save(@RequestParam(required = true) String tbPostsPostJson,
						   @RequestParam(required = true) String optsBy){
		// 受影响行数
		int result = 0;

		/*
		 * json转换对象
		 */
		TbPostsPost tbPostsPost = null;
		try {
			tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
		}catch (Exception e){
			e.printStackTrace();
		}

		/*
		 * 修改新增
		 */
		if (tbPostsPost != null){
			// 新增
			if (StringUtils.isBlank(tbPostsPost.getPostGuid())){
				postsService.insert(tbPostsPost,optsBy);
			}else {
				// 修改
				postsService.update(tbPostsPost,optsBy);
			}
			if (result > 0){
				return BaseResult.ok("保存文章成功");
			}
		}
		return BaseResult.ok("保存文章失败");
	}

	/**
	 * @author PDA
	 * @Date 2022/10/9 19:07
	 * @Description 分页查询
	 * @Param [pageNum, pageSize, tbPostsPostJson]
	 * @return com.pda.itoken.common.dto.BaseResult
	 * @since version-1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
	public BaseResult page(@PathVariable(required = true) int pageNum,
						   @PathVariable(required = true) int pageSize,
						   @PathVariable(required = false) String tbPostsPostJson) throws Exception{
		/*
		 * json转换对象
		 */
		TbPostsPost tbPostsPost = null;
		if (StringUtils.isNotBlank(tbPostsPostJson)){
			tbPostsPost = MapperUtils.json2pojo(tbPostsPostJson, TbPostsPost.class);
		}

		/*
		 * 分页查询
		 */
		PageInfo<TbPostsPost> pageInfo = postsService.page(pageNum,pageSize,tbPostsPost);
		List<TbPostsPost> tbPostsPostList = pageInfo.getList();

		// 分页参数
		BaseResult.Cursor cursor = new BaseResult.Cursor();
		// 总页数
		cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
		// 当前位置
		cursor.setOffset(pageInfo.getPageNum());
		// 每页显示数量
		cursor.setLimit(pageSize);
		return BaseResult.ok(tbPostsPostList,cursor);
	}
}
