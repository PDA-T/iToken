package com.pda.itoken.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pda.itoken.common.domain.BaseDomain;
import com.pda.itoken.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MyMapper;

import java.util.Date;

/**
 * 基础实现类
 */
@Service
@Transactional(readOnly = true)// 开启只读
public class BaseServiceImpl<T extends BaseDomain,D extends MyMapper<T>> implements BaseService<T> {

	// Mapper
	@Autowired
	private D dao;

	/**
	 * 新增
	 * @param t
	 * @param createBy
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)// 关闭只读
	public int insert(T t,String createBy) {
		t.setCreateBy(createBy);
		t.setCreateDate(new Date());
		return dao.insert(t);
	}

	/**
	 * 删除
	 * @param t
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)// 关闭只读
	public int delete(T t) {
		return dao.delete(t);
	}

	/**
	 * 修改
	 * @param t
	 * @param updateBy
	 * @return
	 */
	@Override
	@Transactional(readOnly = false)// 关闭只读
	public int update(T t,String updateBy) {
		t.setUpdateBy(updateBy);
		t.setUpdateDate(new Date());
		// 根据主键更新
		return dao.updateByPrimaryKey(t);
	}

	/**
	 * 查询数量
	 * @param t
	 * @return
	 */
	@Override
	public int count(T t) {
		return dao.selectCount(t);
	}

	/**
	 * 查询单个
	 * @param t
	 * @return
	 */
	@Override
	public T selectOne(T t) {
		return (T) dao.selectOne(t);
	}

	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	public PageInfo<T> page(int pageNum, int pageSize,T t) {
		PageHelper pageHelper = new PageHelper();
		pageHelper.startPage(pageNum,pageSize);
		PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
		return pageInfo;
	}
}
