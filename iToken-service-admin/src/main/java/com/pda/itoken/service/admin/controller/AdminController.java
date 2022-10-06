package com.pda.itoken.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.pda.itoken.common.domain.TbSysUser;
import com.pda.itoken.common.dto.BaseResult;
import com.pda.itoken.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户Controller
 */
@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {

	// 用户
	@Autowired
	private AdminService adminService;

	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param tbSysUser
	 * @return
	 */
	@RequestMapping(value = "page/{pageNum}/{pageSize}",method = RequestMethod.GET)
	public BaseResult page(@PathVariable(required = true) int pageNum,
						   @PathVariable(required = true) int pageSize,
						   @PathVariable(required = false) TbSysUser tbSysUser){
		// 分页数据
		PageInfo pageInfo = adminService.page(pageNum, pageSize, tbSysUser);
		// 结果集
		List<TbSysUser> list = pageInfo.getList();
		// 封装分页数据
		BaseResult.Cursor cursor = new BaseResult.Cursor();
		cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
		cursor.setOffset(pageInfo.getPageNum());
		cursor.setLimit(pageInfo.getPageSize());
		return BaseResult.ok(list,cursor);
	}
}
