package com.program.haohu.business.service;

import com.program.haohu.entity.admin.News;

import java.util.List;

/**
 * @ClassName:
 * @Description: 收藏服务接口
 * @author:
 * @date: 2020年04月26日 17:45
 * @Copyright:
 */
public interface FavoriteService {
    //添加收藏
    public void add(Integer newsId,Integer userId);
    //取消收藏
    public void delete(Integer newsId,Integer userId);

    //查询我所有的收藏
    public List<News> queryMyFavoriteNews(Integer userId);
}
