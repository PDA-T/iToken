package com.pda.itoken.web.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author PDA
 * @Date 2022/10/28 19:16
 * @Description 主页面控制器
 * @since version-1.0
 */
@Controller
public class MainController {

	/**
	 * @author PDA
	 * @Date 2022/10/28 19:17
	 * @Description 跳转主页面
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "",method = RequestMethod.GET)
	public String main(){
		return "main";
	}

	/**
	 * @author PDA
	 * @Date 2022/10/28 19:18
	 * @Description 跳转欢迎页面
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "welcome",method = RequestMethod.GET)
	public String welcome(){
		return "welcome";
	}
}
