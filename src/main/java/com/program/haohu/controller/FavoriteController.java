package com.program.haohu.controller;

import com.program.haohu.business.service.FavoriteService;
import com.program.haohu.entity.admin.User;
import com.program.haohu.service.admin.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月26日 17:33
 * @Copyright:
 */
@Controller
@RequestMapping("/fav")
public class FavoriteController extends BaseController {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private NewsCategoryService newsCategoryService;
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(Integer newsId, HttpSession session){
        try {
            Object user = session.getAttribute("_login_user_");
            if (user==null){
                AjaxResult.error("用户没登录!");
            }
            favoriteService.add(newsId, ((User) user).getId().intValue());
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @PostMapping("/del")
    @ResponseBody
    public AjaxResult del(Integer newsId, HttpSession session){
        try {
            Object user = session.getAttribute("_login_user_");
            if (user==null){
                AjaxResult.error("用户没登录!");
            }
            favoriteService.delete(newsId, ((User) user).getId().intValue());
        }catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    //跳转到我的
    @GetMapping("/myfvorite")
    public ModelAndView myFavoriteView(HttpSession session){
        User login_user_ = (User) session.getAttribute("_login_user_");
        ModelAndView model=new ModelAndView("/home/index/myfavorite");
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        //model.addObject("newsCategory", new NewsCategory());
        model.addObject("title", "我的收藏");
        model.addObject("newsList", favoriteService.queryMyFavoriteNews(login_user_.getId().intValue()));
        return model;
    }
}
