package com.program.haohu.business.service;

import com.program.haohu.entity.admin.News;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:
 * @Description: 推荐服务接口
 * @author:
 * @date: 2020年04月27日 8:55
 * @Copyright:
 */
public interface RecommendService {
    public News recommend(HttpSession session);
}
