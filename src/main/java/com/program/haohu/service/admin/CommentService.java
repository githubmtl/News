package com.program.haohu.service.admin;

import com.program.haohu.entity.admin.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 新闻评论接口
 *
 * @author hh
 */
@Service
public interface CommentService {
    public int add(Comment comment);
    public int edit(Comment comment);
    public int delete(String ids); // 批量删除
    public List<Comment> findList(Map<String, Object> queryMap);
    public List<Comment> findAll();
    public int getTotal(Map<String, Object> queryMap);
}
