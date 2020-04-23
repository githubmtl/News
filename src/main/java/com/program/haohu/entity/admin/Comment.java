package com.program.haohu.entity.admin;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 新闻评论实体
 *
 * @author hh
 */
@Component
public class Comment {
    private Long id;
    private Long newsId; // 新闻id
    private News news; // 新闻实体
    private String nickname; // 昵称
    private String content; // 评论内容
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
