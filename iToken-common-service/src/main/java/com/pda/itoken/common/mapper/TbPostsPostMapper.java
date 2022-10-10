package com.pda.itoken.common.mapper;

import com.pda.itoken.common.domain.TbPostsPost;
import com.pda.itoken.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)// 二级缓存
public interface TbPostsPostMapper extends MyMapper<TbPostsPost> {
}