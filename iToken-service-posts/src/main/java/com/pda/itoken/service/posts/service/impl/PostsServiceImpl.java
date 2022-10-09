package com.pda.itoken.service.posts.service.impl;

import com.pda.itoken.common.domain.BaseDomain;
import com.pda.itoken.common.domain.TbPostsPost;
import com.pda.itoken.common.mapper.TbPostsPostMapper;
import com.pda.itoken.common.service.impl.BaseServiceImpl;
import com.pda.itoken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PDA
 * @Date 2022/10/9 18:28
 * @Description 文章Service业务层处理
 * @since version-1.0
 */
@Service
@Transactional(readOnly = true)// 开启只读
public class PostsServiceImpl extends BaseServiceImpl<TbPostsPost, TbPostsPostMapper> implements PostsService<TbPostsPost> {
}
