package com.program.haohu.service.admin.impl;

import com.program.haohu.dao.admin.NewsDao;
import com.program.haohu.entity.admin.News;
import com.program.haohu.service.admin.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻service实现类
 *
 * @author hh
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public int add(News news) {
        return newsDao.add(news);
    }

    @Override
    public int edit(News news) {
        return newsDao.edit(news);
    }

    @Override
    public int delete(Long id) {
        return newsDao.delete(id);
    }

    @Override
    public List<News> findList(Map<String, Object> queryMap) {
        return newsDao.findList(queryMap);
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return newsDao.getTotal(queryMap);
    }

    @Override
    public News find(Long id) {
        return newsDao.find(id);
    }

    @Override
    public int updateCommentNumber(Long id) {
        return newsDao.updateCommentNumber(id);
    }

    @Override
    public int updateViewNumber(Long id) {
        return newsDao.updateViewNumber(id);
    }

    @Override
    public List<News> findLastCommentList(int pageSize) {
        return newsDao.findLastCommentList(pageSize);
    }
}
