package com.pda.itoken.web.posts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author PDA
 * @Date 2022/10/14 22:23
 * @Description 文章页面控制器
 * @since version-1.0
 */
@Controller
public class PostsController {
	/**
	 * @author PDA
	 * @Date 2022/10/14 22:24
	 * @Description 跳转首页
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = {"","index"},method = RequestMethod.GET)
	public String index(){
		return "index";
	}
}
