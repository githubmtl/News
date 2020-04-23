package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.News;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻接口
 *
 * @author hh
 */
@Service
public interface NewsService {
    public int add(News news);
    public int edit(News news);
    public int delete(Long id);
    public List<News> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
    public News find(Long id);
    public int updateCommentNumber(Long id);
    public int updateViewNumber(Long id);
    public List<News> findLastCommentList(int pageSize);
}
