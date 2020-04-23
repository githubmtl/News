package com.program.haohu.controller.home;

import com.program.haohu.service.admin.NewsCategoryService;
import com.program.haohu.service.admin.NewsService;
import com.program.haohu.util.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台页面首页控制器
 *
 * @author hh
 */
@RequestMapping("/index")
@Controller
public class IndexController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private NewsService newsService;

    /**
     * 新闻系统首页
     * @param model
     * @return
     * @author hh
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 10);
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        model.addObject("newsList", newsService.findList(queryMap));
        // 设置jsp页面所在位置为src/main/webapp/WEB-INF/views/home/index
        model.setViewName("home/index/index");
        return model;
    }

    /**
     * 用户登录页面
     * @param model
     * @return
     * @author hh
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
        model.setViewName("home/index/login");
        return model;
    }

    /**
     * 验证码
     * @param cpachaType：用来区别验证码的类型，传入字符串
     * @param vcodeLen
     * @param width
     * @param height
     * @param request
     * @param response
     * @author hh
     */
    @RequestMapping(value = "/get_cpacha", method = RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name = "vl", required = false, defaultValue = "4") Integer vcodeLen,
            @RequestParam(name = "w", required = false, defaultValue = "110") Integer width,
            @RequestParam(name = "h", required = false, defaultValue = "30") Integer height,
            @RequestParam(name = "type", required = false, defaultValue = "loginCpacha") String cpachaType,
            /*@RequestParam(name = "type", required = false, defaultValue = "registerCpacha") String cpachaType1,*/
            HttpServletRequest request,
            HttpServletResponse response) {
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute(cpachaType, generatorVCode);
        BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户注册页面
     * @param model
     * @return
     * @author hh
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(ModelAndView model) {
        model.setViewName("home/index/register");
        return model;
    }

    /**
     * 获取网站基本信息
     * @return
     * @author hh
     */
    @RequestMapping(value = "/get_site_info",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 99999);
        ret.put("type", "success");
        // "totalArticle"对应home/common/sidebar.jsp中if (data.type == 'success'){
        //                $("#total-article-span").text(data.totalArticle);}中的totalArticle
        ret.put("totalArticle", newsService.getTotal(queryMap));
        // "siteDays"对应home/common/sidebar.jsp中if (data.type == 'success'){
        //                $("#sitetime").text(data.siteDays);}中的siteDays
        ret.put("siteDays", getDays("2020-02-15"));
        return ret;
    }

    private long getDays(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = new Date();
        // 天数
        long time = (endDate.getTime() - startDate.getTime())/1000/3600/24;
        return time;
    }

}
