package com.program.haohu.controller.home;

import com.program.haohu.business.service.HisService;
import com.program.haohu.business.service.RecommendService;
import com.program.haohu.dao.admin.UserCollectNewsDao;
import com.program.haohu.entity.admin.*;
import com.program.haohu.page.admin.Page;
import com.program.haohu.service.admin.CommentService;
import com.program.haohu.service.admin.NewsCategoryService;
import com.program.haohu.service.admin.NewsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台新闻控制器
 *
 * @author hh
 */
@RequestMapping("/news")
@Controller
public class HomeNewsController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private HisService hisService;

    @Autowired
    private UserCollectNewsDao userCollectNewsDao;

    @Autowired
    private RecommendService recommendService;



    /**
     * 获取按评论数排序的最新n条信息
     * @return
     * @author hh
     */
    // last_comment_list对应src/main/webapp/WEB-INF/views/home/common/sidebar.jsp中$.ajax({
    //            url:'../news/last_comment_list',中的last_comment_list
    @RequestMapping(value = "/last_comment_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> lastCommentList() {
        Map<String, Object> ret = new HashMap<String, Object>();
        ret.put("type", "success");
        // newsList对应home/common/sidebar.jsp中ajax请求中success:function(data) {
        //            if (data.type == 'success'){
        //                var newsList = data.newsList;中data.newsList中的newsList
        ret.put("newsList", newsService.findLastCommentList(10));
        return ret;
    }

    /**
     * 按照分类显示新闻列表
     * @param model
     * @param cid
     * @param page
     * @return
     * @author hh
     */
    // category_list对应home/news/category_list.jsp中<a class="cat" href="../news/category_list?cid=${news.categoryId }"中的category_list
    @RequestMapping(value = "/category_list",method = RequestMethod.GET)
    public ModelAndView categoryList(ModelAndView model,
            // 定义的cid这个参数，就是在浏览器中url后面?后面的参数，比如：localhost:8080/news/category_list?cid=4
            @RequestParam(name = "cid", required = true) Long cid,
            Page page,HttpSession session
            ) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 10);
        // cid对应上面@RequestParam(name = "cid", required = true) Long cid,中定义的cid
        // categoryId对应src/main/java/config/mybatis/mapper/admin/NewsMapper中findList方法中
        // <if test="categoryId != null"> and n.categoryId = #{categoryId} </if>中#{categoryId}中的categoryId
        queryMap.put("categoryId", cid);
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        // "newsList"的对应src/main/webapp/WEB-INF/views/home/news/category_list.jsp中<c:forEach items="${newsList }" var="news">中的newsList
        model.addObject("newsList", newsService.findList(queryMap));
        NewsCategory newsCategory = newsCategoryService.find(cid);
        // "newsCategory" 对应home/news/category_list.jsp中第14行<div class="title">
        //                <h3>${newsCategory.name }</h3>中的newsCategory
        model.addObject("newsCategory", newsCategory);
        // title对应src/main/webapp/WEB-INF/views/home/common/header.jsp中<title>HH新闻${title }</title>中${title }
        model.addObject("title", newsCategory.getName() + "分类下的新闻信息");
        model.addObject("tj_news", recommendService.recommend(session));
        model.setViewName("home/news/category_list");
        return model;
    }

    /**
     * 分页获取某个分类下的文章
     * @param page
     * @param cid
     * @return
     * @author hh
     */
    // get_category_list对应src/main/webapp/WEB-INF/views/home/news/category_list.jsp中$.ajax({
    //            url:'../news/get_category_list',中的get_category_list
    @RequestMapping(value = "/get_category_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCategoryList(Page page,
            @RequestParam(name = "cid", required = true) Long cid
            ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        // categoryId对应src/main/java/config/mybatis/mapper/admin/NewsMapper中findList方法中
        // <if test="categoryId != null"> and n.categoryId = #{categoryId} </if>中#{categoryId}中的的categoryId
        queryMap.put("categoryId", cid);
        ret.put("type", "success");
        // "newsList"对应home/news/category_list.jsp中ajax请求中success:function(data) {
        //                if (data.type == 'success'){
        //                    var newsList = data.newsList;中data.newsList中的newsList
        ret.put("newsList", newsService.findList(queryMap));
        return ret;
    }

    /**
     * 获取搜索列表
     * @param model
     * @param keyword
     * @param page
     * @return
     * @author hh
     */
    @RequestMapping(value = "/search_list",method = RequestMethod.GET)
    public ModelAndView searchList(ModelAndView model,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            Page page,HttpSession session
            ) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 10);
        // keyword对应上面@RequestParam(name = "keyword", required = true) String keyword,中定义的keyword
        // title对应src/main/java/config/mybatis/mapper/admin/NewsMapper中findList方法中
        // <if test="title != null"> and n.title like '%${title}%' </if>中'%${title}%'中的title
        queryMap.put("title", keyword);
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        // "newsList"对应src/main/webapp/WEB-INF/views/home/news/search_list.jsp中<c:forEach items="${newsList }" var="news">中的newsList
        model.addObject("newsList", newsService.findList(queryMap));
        // title对应src/main/webapp/WEB-INF/views/home/common/header.jsp中<title>HH新闻${title }</title>中${title }
        model.addObject("title", keyword + "关键字下的新闻信息");
        // "keyword"对应src/main/webapp/WEB-INF/views/home/news/search_list.jsp中<h3>${keyword }</h3>中的keyword
        model.addObject("keyword", keyword);
        model.addObject("tj_news", recommendService.recommend(session));
        model.setViewName("home/news/search_list");
        return model;
    }

    /**
     * 分页加载搜索列表
     * @param page
     * @param keyword
     * @return
     * @author hh
     */
    // get_search_list对应src/main/webapp/WEB-INF/views/home/news/search_list.jsp中$.ajax({
    //            url:'../news/get_search_list',中的get_search_list
    @RequestMapping(value = "/get_search_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSearchList(Page page,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword
            ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        queryMap.put("title", keyword);
        ret.put("type", "success");
        // "newsList"对应home/news/search_list.jsp中ajax请求中success:function(data) {
        //                if (data.type == 'success'){
        //                    var newsList = data.newsList;中data.newsList中的newsList
        ret.put("newsList", newsService.findList(queryMap));
        return ret;
    }

    /**
     * 查看新闻详情
     * @param model
     * @param id
     * @return
     * @author hh
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView detail(ModelAndView model, Long id, HttpSession session) {
        model.addObject("newsCategoryList", newsCategoryService.findAll());
        // id对应home/news/category_list.jsp中<a class="focus" href="../news/detail?id=${news.id }"中的id
        News news = newsService.find(id);
        model.addObject("news", news);
        model.addObject("detail_flag", true);
        model.addObject("_newsId_", id);
        // 将新闻的标题传到前台作为网页的title
        model.addObject("title", news.getTitle());
        // 将新闻的标签按逗号(英文,)分割传到前台,tags对应home/news/detail.jsp中<c:forEach items="${tags }" var="tag">中的tags
        model.addObject("tags", news.getTags().split(","));
        // home/news/detail对应src/main/webapp/WEB-INF/views/home/news/detail.jsp
        model.setViewName("home/news/detail");
        // 新闻浏览量加1
        newsService.updateViewNumber(id);
        //增加历史
        hisService.addHis(id.intValue(),session);
        //是否可以收藏
        Object user = session.getAttribute("_login_user_");
        if (user==null){
            model.addObject("_provaable_", true);
        }else{
            List<UserCollectNews> userCollectNews = userCollectNewsDao.selectByExample(new UserCollectNews(((User) user).getId().intValue(), id.intValue(), null));
            if (CollectionUtils.isEmpty(userCollectNews)){
                model.addObject("_provaable_", true);
            }else{
                model.addObject("_provaable_", false);
            }
        }
        return model;
    }

    /**
     * 添加评论
     * @param comment
     * @return
     * @author hh
     */
    @RequestMapping(value = "/comment_news",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> commentNews(Comment comment) {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (comment == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写完整的评论信息！");
            return ret;
        }
        if (comment.getNewsId() == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择一个文章进行评论！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getNickname())) {
            ret.put("type", "error");
            ret.put("msg", "请填写昵称！");
            return ret;
        }
        if (StringUtils.isEmpty(comment.getContent())) {
            ret.put("type", "error");
            ret.put("msg", "请填写评论内容！");
            return ret;
        }
        comment.setCreateTime(new Date());
        if (commentService.add(comment) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "评论失败，请联系管理员！");
            return ret;
        }
        // 文章评论数加1
        newsService.updateCommentNumber(comment.getNewsId());
        ret.put("type", "success");
        // "createTime"对应home/news/detail.jsp中format(data.createTime)中的createTime
        ret.put("createTime", comment.getCreateTime());
        return ret;
    }

    /**
     * 分页获取某一文章的评论
     * @param page
     * @return
     * @author hh
     */
    @RequestMapping(value = "/get_comment_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCommentList(Page page,Long newsId) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        // newsId对应src/main/java/config/mybatis/mapper/admin/CommentMapper中findList方法中
        // <if test="newsId != null"> and c.newsId = #{newsId} </if>中#{newsId}中的newsId
        queryMap.put("newsId", newsId);
        ret.put("type", "success");
        // "commentList"对应home/news/detail.jsp中ajax请求中success:function(data) {
        //                if (data.type == 'success'){
        //                    var commentList = data.commentList;中data.commentList中的commentList
        ret.put("commentList", commentService.findList(queryMap));
        return ret;
    }

}
