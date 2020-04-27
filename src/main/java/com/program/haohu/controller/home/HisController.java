package com.program.haohu.controller.home;

import com.program.haohu.controller.BaseController;
import com.program.haohu.dao.admin.UserHisNewsDao;
import com.program.haohu.entity.admin.User;
import com.program.haohu.service.admin.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月26日 19:38
 * @Copyright:
 */
@Controller
@RequestMapping("/his")
public class HisController extends BaseController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private UserHisNewsDao hisNewsDao;

    @RequestMapping("/myHis")
    public ModelAndView myHisView(HttpSession session){
        User login_user_ = (User) session.getAttribute("_login_user_");
        ModelAndView model=new ModelAndView("/home/index/myhis");
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        //model.addObject("newsCategory", new NewsCategory());
        model.addObject("title", "我的历史浏览");
        model.addObject("newsList", hisNewsDao.queryMyHisNews(login_user_.getId().intValue()));
        return model;
    }
}
