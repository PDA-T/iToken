package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Mapper
 * 通用的父级接口
 * 不能被扫描
 * @param <T>
 */
public interface MyMapper<T> extends Mapper, MySqlMapper {
}
