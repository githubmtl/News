package com.program.haohu.entity.admin;

import java.util.Date;

/**
 * @ClassName:
 * @Description: 新闻收藏
 * @author:
 * @date: 2020年04月25日 9:56
 * @Copyright:
 */
public class UserCollectNews {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private Date colTime;

    public UserCollectNews(Integer userId, Integer newsId, Date colTime) {
        this.userId = userId;
        this.newsId = newsId;
        this.colTime = colTime;
    }

    public UserCollectNews() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    @Override
    public String toString() {
        return "UserCollectNews{" +
                "id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                ", colTime=" + colTime +
                '}';
    }
}
