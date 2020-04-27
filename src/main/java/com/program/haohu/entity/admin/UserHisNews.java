package com.program.haohu.entity.admin;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName:
 * @Description: 浏览历史
 * @author:
 * @date: 2020年04月25日 9:49
 * @Copyright:
 */
public class UserHisNews {
    private Integer id;
    private Integer userId;
    private Integer newsId;
    private Date lastViewTime;


    public UserHisNews(Integer userId, Integer newsId) {
        this.userId = userId;
        this.newsId = newsId;
    }

    public UserHisNews() {
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

    public Date getLastViewTime() {
        return lastViewTime;
    }

    public void setLastViewTime(Date lastViewTime) {
        this.lastViewTime = lastViewTime;
    }

    @Override
    public String toString() {
        return "UserHisNews{" +
                "id=" + id +
                ", userId=" + userId +
                ", newsId=" + newsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHisNews that = (UserHisNews) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(newsId, that.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, newsId);
    }
}
