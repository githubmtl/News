package com.program.haohu.service.admin.impl;

import com.program.haohu.dao.admin.NewsCategoryDao;
import com.program.haohu.entity.admin.NewsCategory;
import com.program.haohu.service.admin.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻分类service实现类
 *
 * @author hh
 */
@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    @Autowired
    private NewsCategoryDao newsCategoryDao;

    @Override
    public int add(NewsCategory newsCategory) {
        return newsCategoryDao.add(newsCategory);
    }

    @Override
    public int edit(NewsCategory newsCategory) {
        return newsCategoryDao.edit(newsCategory);
    }

    @Override
    public int delete(Long id) {
        return newsCategoryDao.delete(id);
    }

    @Override
    public List<NewsCategory> findList(Map<String, Object> queryMap) {
        return newsCategoryDao.findList(queryMap);
    }

    @Override
    public List<NewsCategory> findAll() {
        return newsCategoryDao.findAll();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return newsCategoryDao.getTotal(queryMap);
    }

    @Override
    public NewsCategory find(Long id) {
        return newsCategoryDao.find(id);
    }
}
