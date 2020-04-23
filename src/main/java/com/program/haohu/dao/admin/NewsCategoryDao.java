package com.program.haohu.dao.admin;

import com.program.haohu.entity.admin.NewsCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 新闻分类Dao
 *
 * @author hh
 */
@Repository
public interface NewsCategoryDao {
    public int add(NewsCategory newsCategory);
    public int edit(NewsCategory newsCategory);
    public int delete(Long id);
    public List<NewsCategory> findList(Map<String, Object> queryMap);
    public List<NewsCategory> findAll();
    public int getTotal(Map<String, Object> queryMap);
    public NewsCategory find(Long id);
}
