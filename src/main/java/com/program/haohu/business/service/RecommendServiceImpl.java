package com.program.haohu.business.service;

import com.program.haohu.dao.admin.NewsDao;
import com.program.haohu.dao.admin.UserHisNewsDao;
import com.program.haohu.entity.admin.News;
import com.program.haohu.entity.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName:
 * @Description: 推荐服务类
 * @author:
 * @date: 2020年04月27日 8:56
 * @Copyright:
 */
@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private NewsDao newsDao;

    @Autowired
    private UserHisNewsDao userHisNewsDao;
    @Override
    public News recommend(HttpSession session) {
        Object user = session.getAttribute("_login_user_");
        if (user==null){
            return newsDao.queryHotWithoutSession(null);
        }
        User u = (User) user;
        Map<String, Object> userLikes = userHisNewsDao.selectUserLike(u.getId().intValue());
        if (userLikes==null||userLikes.size()==0){//用户没有浏览历史
            return newsDao.queryHotWithoutSession(null);
        }
        return newsDao.queryHotWithoutSession(Integer.parseInt(userLikes.get("categoryId").toString()));//返回用户浏览最多栏目下的最火新闻
    }
}
