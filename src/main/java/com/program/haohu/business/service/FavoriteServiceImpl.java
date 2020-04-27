package com.program.haohu.business.service;

import com.program.haohu.dao.admin.UserCollectNewsDao;
import com.program.haohu.entity.admin.News;
import com.program.haohu.entity.admin.UserCollectNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月26日 17:46
 * @Copyright:
 */
@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private UserCollectNewsDao collectNewsDao;
    @Override
    public void add(Integer newsId, Integer userId) {
        UserCollectNews example = new UserCollectNews(userId, newsId, new Date());
        List<UserCollectNews> userCollectNews = collectNewsDao.selectByExample(example);
        if (!CollectionUtils.isEmpty(userCollectNews)){
            throw new RuntimeException("该文章已经收藏！");
        }
        collectNewsDao.add(example);
    }

    @Override
    public void delete(Integer newsId, Integer userId) {
        collectNewsDao.deleteByExample(new UserCollectNews(userId,newsId,null));
    }

    @Override
    public List<News> queryMyFavoriteNews(Integer userId) {
        return collectNewsDao.queryMyfavoriteNews(userId);
    }
}
