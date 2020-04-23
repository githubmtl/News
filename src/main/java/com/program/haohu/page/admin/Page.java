package com.program.haohu.page.admin;

import org.springframework.stereotype.Component;

/**
 * 分页基本信息
 *
 * @author hh
 */
@Component
public class Page {
    private int page = 1; // 起始页码

    // 分页查询sql语句：select * from table limit (start-1)*pageSize,pageSize
    private int rows; // 每页显示的条数，即：pageSize

    private int offset; // 对应数据库中的偏移量，即：(start-1)*pageSize

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        this.offset = (page - 1) * rows;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
