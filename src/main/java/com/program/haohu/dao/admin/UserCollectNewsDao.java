package com.program.haohu.dao.admin;

import com.program.haohu.entity.admin.News;
import com.program.haohu.entity.admin.UserCollectNews;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月25日 10:13
 * @Copyright:
 */
public interface UserCollectNewsDao {
    //添加收藏
    public int add(UserCollectNews userCollectNews);
    //删除收藏
    public int delete(int id);
    //删除收藏
    public int deleteByExample(UserCollectNews example);
    //查询收藏
    public List<UserCollectNews> selectByExample(UserCollectNews example);
    //查询我的收藏
    public List<News> queryMyfavoriteNews(Integer userId);
}
