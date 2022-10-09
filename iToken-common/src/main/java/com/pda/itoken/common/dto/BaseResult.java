package com.pda.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 基础数据传输对象
 */
@Data
public class BaseResult implements Serializable {
	public static final String RESULT_OK = "ok";
	public static final String RESULT_NOT_OK = "not_ok";
	public static final String SUCCESS = "成功操作";
	// 状态
	private String result;

	// 返回的具体数据
	private Object data;

	// 成功提示
	private String success;

	// 分页
	private Cursor cursor;

	// 错误信息
	private List<Error> errors;

	/**
	 * 无参成功
	 * @return
	 */
	public static BaseResult ok(){
		return createResult(RESULT_OK,null,SUCCESS,null,null);
	}

	/**
	 * @Author PDA
	 * @Date 2022/10/9 19:01
	 * @Description 字符串消息
	 * @Param [success]
	 * @Return com.pda.itoken.common.dto.BaseResult
	 * @Since version-1.0
	 */
	public static BaseResult ok(String success){
		return createResult(RESULT_OK,null,SUCCESS,null,null);
	}

	/**
	 * 有参成功
	 * @param data
	 * @return
	 */
	public static BaseResult ok(Object data){
		return createResult(RESULT_OK,data,SUCCESS,null,null);
	}

	/**
	 * 分页成功
	 * @param data
	 * @param cursor
	 * @return
	 */
	public static BaseResult ok(Object data,Cursor cursor){
		return createResult(RESULT_OK,data,SUCCESS,cursor,null);
	}

	/**
	 * 失败
	 * @param errors
	 * @return
	 */
	public static BaseResult notOk(List<BaseResult.Error> errors){
		return createResult(RESULT_NOT_OK,null,"",null,errors);
	}

	/**
	 * 封装
	 * @param result
	 * @param data
	 * @param success
	 * @param cursor
	 * @param errors
	 * @return
	 */
	private static BaseResult createResult(String result,Object data,String success,Cursor cursor,List<Error> errors){
		BaseResult baseResult = new BaseResult();
		baseResult.setResult(result);
		baseResult.setData(data);
		baseResult.setSuccess(success);
		baseResult.setCursor(cursor);
		baseResult.setErrors(errors);
		return baseResult;
	}

	/**
	 * 分页
	 */
	@Data
	public static class Cursor{
		// 全部条数
		private int total;

		// 当前所在位置
		private int offset;

		// 每页条数
		private int limit;
	}

	/**
	 * 错误信息
	 */
	@Data
	@AllArgsConstructor// 全参构造
	public static class Error{
		// 错误字段
		private String field;

		// 错误消息
		private String message;
	}
}
