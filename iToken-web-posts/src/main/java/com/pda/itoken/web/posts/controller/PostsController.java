package com.pda.itoken.web.posts.controller;

import com.pda.itoken.common.domain.TbPostsPost;
import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.common.web.constants.WebConstants;
import com.pda.itoken.common.web.controller.BaseController;
import com.pda.itoken.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @author PDA
 * @Date 2022/10/14 22:23
 * @Description 文章页面控制器
 * @since version-1.0
 */
@Controller
public class PostsController extends BaseController<TbPostsPost, PostsService> {

	@Autowired
	private PostsService postsService;

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:37
	 * @Description ModelAttribute方法会在RequestMapping之前执行
	 * @Param [postGuid]
	 * @return com.pda.itoken.common.domain.TbPostsPost
	 * @since version-1.0
	 */
	@ModelAttribute
	public TbPostsPost tbPostsPost(String postGuid){
		TbPostsPost tbPostsPost = null;
		if (StringUtils.isBlank(postGuid)){
			tbPostsPost = new TbPostsPost();
		}else {
			String json = postsService.get(postGuid);
			try {
				tbPostsPost = MapperUtils.json2pojo(json, TbPostsPost.class);
			}catch (Exception e){
				e.printStackTrace();
			}
			// 防止空报错
			if (tbPostsPost == null){
				tbPostsPost = new TbPostsPost();
			}
		}
		return tbPostsPost;
	}
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

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:21
	 * @Description 跳转表单页面
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "form",method = RequestMethod.GET)
	public String form(){
		return "form";
	}

	/**
	 * @author PDA
	 * @Date 2022/10/16 19:45
	 * @Description 保存文章
	 * @Param [tbPostsPost,request,redirectAttributes]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@RequestMapping(value = "save",method = RequestMethod.POST)
	public String save(TbPostsPost tbPostsPost, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
		// 初始化
		tbPostsPost.setTimePublished(new Date());
		tbPostsPost.setStatus("0");
		// 从会话拿到用户对象
		TbSysUser admin = (TbSysUser) request.getSession().getAttribute(WebConstants.SESSION_USER);
		// 对象转换json
		String tbPostsPostJson = MapperUtils.obj2json(tbPostsPost);
		String json = postsService.save(tbPostsPostJson, admin.getUserCode());
		BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
		// 带回参数
		redirectAttributes.addFlashAttribute("baseResult",baseResult);
		// json返回成功
		if (baseResult.getSuccess().endsWith("成功")){
			// 跳转首页
			return "redirect:/index";
		}
		return "redirect:/form";
	}
}
