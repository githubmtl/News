package com.program.haohu.dao.admin;

import com.program.haohu.entity.admin.News;
import com.program.haohu.entity.admin.UserHisNews;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月25日 9:59
 * @Copyright:
 */
public interface UserHisNewsDao {
    /**
    * @Description: 批量插入
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\26 0026
    */
    public int addAll(List<UserHisNews> lists);
    /**
    * @Description: 添加一个历史记录
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\26 0026
    */
    public int add(UserHisNews userHisNews);
    /**
    * @Description:  更新一个历史记录
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\26 0026
    */
    public int updateByUserNewsHis(UserHisNews userHisNews);
    /**
    * @Description:  条件查询
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\26 0026
    */
    public List<UserHisNews> selectByUserNewsHis(UserHisNews example);

    /**
    * @Description: 查询我的历史浏览
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\26 0026
    */
    public List<News> queryMyHisNews(Integer userId);

    /**
    * @Description:  查询用户最喜欢的栏目
    * @Param:
    * @return:
    * @Author:
    * @Exception
    * @Date: 2020\4\27 0027
    */
    public Map<String,Object> selectUserLike(Integer userId);
}
