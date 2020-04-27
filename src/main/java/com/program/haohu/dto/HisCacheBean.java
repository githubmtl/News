package com.program.haohu.dto;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName:
 * @Description:
 * @author:
 * @date: 2020年04月26日 15:49
 * @Copyright:
 */
public class HisCacheBean {
    private Integer newsId;
    private Date viewTime;

    public HisCacheBean(Integer newsId, Date viewTime) {
        this.newsId = newsId;
        this.viewTime = viewTime;
    }

    public HisCacheBean() {
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getViewTime() {
        return viewTime;
    }

    public void setViewTime(Date viewTime) {
        this.viewTime = viewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HisCacheBean that = (HisCacheBean) o;
        return Objects.equals(newsId, that.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId);
    }
}
