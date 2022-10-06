package com.pda.itoken.common.service;

import com.github.pagehelper.PageInfo;
import com.pda.itoken.common.domain.BaseDomain;

/**
 * 基础接口
 */
public interface BaseService<T extends BaseDomain> {

	/**
	 * 新增
	 * @param t
	 * @param createBy
	 * @return
	 */
	public int insert(T t,String createBy);

	/**
	 * 删除
	 * @param t
	 * @return
	 */
	public int delete(T t);

	/**
	 * 修改
	 * @param t
	 * @param updateBy
	 * @return
	 */
	public int update(T t,String updateBy);

	/**
	 * 查询数量
	 * @param t
	 * @return
	 */
	public int count(T t);

	/**
	 * 查询单个
	 * @param t
	 * @return
	 */
	public T selectOne(T t);

	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param t
	 * @return
	 */
	public PageInfo<T> page(int pageNum,int pageSize,T t);
}
