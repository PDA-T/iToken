package com.pda.itoken.service.posts.mapper;

import com.pda.itoken.common.domain.TbPostsPost;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

/**
 * @author PDA
 * @Date 2022/10/9 18:28
 * @Description 文章服务Mapper
 * @since version-1.0
 */
@Repository
public interface TbPostsPostExtendMapper extends MyMapper<TbPostsPost> {
}