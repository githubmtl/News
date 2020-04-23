package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.NewsCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻分类接口
 *
 * @author hh
 */
@Service
public interface NewsCategoryService {
    public int add(NewsCategory newsCategory);
    public int edit(NewsCategory newsCategory);
    public int delete(Long id);
    public List<NewsCategory> findList(Map<String, Object> queryMap);
    public List<NewsCategory> findAll();
    public int getTotal(Map<String, Object> queryMap);
    public NewsCategory find(Long id);
}
