package com.pda.itoken.common.web.controller;

import com.pda.itoken.common.domain.BaseDomain;
import com.pda.itoken.common.utils.MapperUtils;
import com.pda.itoken.common.web.components.datatables.DataTablesResult;
import com.pda.itoken.common.web.service.BaseClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PDA
 * @Date 2022/10/16 18:16
 * @Description 通用 Controller
 * @since version-1.0
 */
public abstract class BaseController<T extends BaseDomain,S extends BaseClientService> {

	// 业务逻辑层
	@Autowired
	protected S service;

	public DataTablesResult page(HttpServletRequest request){
		String strDraw = request.getParameter("draw");
		String strStart = request.getParameter("start");
		String strLength = request.getParameter("length");

		int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
		int start = strStart == null ? 0 : Integer.parseInt(strStart);
		int length = strLength == null ? 0 : Integer.parseInt(strLength);

		String json = service.page(start,length,null);
		DataTablesResult dataTablesResult = null;
		try {
			dataTablesResult = MapperUtils.json2pojo(json,DataTablesResult.class);
			dataTablesResult.setDraw(draw);
			dataTablesResult.setRecordsTotal(dataTablesResult.getCursor().getTotal());
			dataTablesResult.setRecordsFiltered(dataTablesResult.getCursor().getTotal());
		}catch (Exception e){
			e.printStackTrace();
		}
		return dataTablesResult;
	}
}
