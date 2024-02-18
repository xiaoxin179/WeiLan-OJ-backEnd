package com.xiaoxin.WeiLanOJ.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxin.WeiLanOJ.model.entity.Post;
import com.xiaoxin.WeiLanOJ.model.entity.PostFavour;
import com.xiaoxin.WeiLanOJ.model.entity.User;

/**
 * 帖子收藏服务
 *
 * @author <a href="https://github.com/lixiaoxin">程序员xiaoxin</a>
 * @from <a href="https://xiaoxin.icu">编程导航知识星球</a>
 */
public interface PostFavourService extends IService<PostFavour> {

    /**
     * 帖子收藏
     *
     * @param postId
     * @param loginUser
     * @return
     */
    int doPostFavour(long postId, User loginUser);

    /**
     * 分页获取用户收藏的帖子列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, Wrapper<Post> queryWrapper,
            long favourUserId);

    /**
     * 帖子收藏（内部服务）
     *
     * @param userId
     * @param postId
     * @return
     */
    int doPostFavourInner(long userId, long postId);
}