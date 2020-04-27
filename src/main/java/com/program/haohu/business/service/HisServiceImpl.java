package com.program.haohu.business.service;

import com.program.haohu.dao.admin.UserHisNewsDao;
import com.program.haohu.dto.HisCacheBean;
import com.program.haohu.entity.admin.User;
import com.program.haohu.entity.admin.UserHisNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月25日 13:45
 * @Copyright:
 */
@Service
@Transactional
public class HisServiceImpl implements HisService {
    @Autowired
    private UserHisNewsDao hisNewsDao;
    @Override
    public void addHis(Integer newsId, HttpSession session) {
        Object loginUser = session.getAttribute("_login_user_");
        Object histroy_news_ = session.getAttribute("_histroy_news_");
        if (histroy_news_==null){
            Set<HisCacheBean> hisNewIds=new HashSet<>();
            session.setAttribute("_histroy_news_",hisNewIds);
        }
        Set<HisCacheBean> hisNews = (Set<HisCacheBean>) session.getAttribute("_histroy_news_");
        if (loginUser==null){//用户没有登录，放在Session里面
            hisNews.add(new HisCacheBean(newsId,new Date()));
        }else{
            User user = (User) loginUser;
            Long id = user.getId();
            UserHisNews un=new UserHisNews(id.intValue(),newsId);
            List<UserHisNews> userHisNews = hisNewsDao.selectByUserNewsHis(un);
            if (CollectionUtils.isEmpty(userHisNews)){
                un.setLastViewTime(new Date());
                hisNewsDao.add(un);
            }else {
                UserHisNews updateBean = userHisNews.get(0);
                updateBean.setLastViewTime(new Date());
                hisNewsDao.updateByUserNewsHis(updateBean);
            }
        }
    }

    @Override
    public void loginHisInit(HttpSession session) {
        Object loginUser = session.getAttribute("_login_user_");
        Object histroy_news_ = session.getAttribute("_histroy_news_");
        if (histroy_news_==null){
            Set<HisCacheBean> hisNewIds=new HashSet<>();
            session.setAttribute("_histroy_news_",hisNewIds);
        }
        Integer id= ((User) loginUser).getId().intValue();
        Set<HisCacheBean> hisNews = (Set<HisCacheBean>) session.getAttribute("_histroy_news_");
        if (!CollectionUtils.isEmpty(hisNews)){
            List<UserHisNews> addList=new ArrayList<>();
            for (HisCacheBean hisNew : hisNews) {
                UserHisNews un=new UserHisNews(id.intValue(),hisNew.getNewsId());
                List<UserHisNews> userHisNews = hisNewsDao.selectByUserNewsHis(un);
                if (!CollectionUtils.isEmpty(userHisNews)){//存在则更新
                    userHisNews.get(0).setLastViewTime(hisNew.getViewTime());
                    hisNewsDao.updateByUserNewsHis(userHisNews.get(0));
                }else{
                    un.setLastViewTime(hisNew.getViewTime());
                    addList.add(un);
                }
            }
            if (addList.size()>0){
                hisNewsDao.addAll(addList);
            }
            hisNews.removeAll(hisNews);
        }
    }
}
