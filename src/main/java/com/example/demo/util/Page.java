package com.example.demo.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@ToString
public class Page<T> {
    @Setter
    private int currentPage = 1; // 当前页
    @Getter
    @Setter
    private int pageSize = 20; //每页显示记录数
    private int startRecord = 1; //起始查询记录
    @Setter
    private int totalPage = 0; //总页数
    private int totalRecord = 0; //总记录数
    @Getter
    @Setter
    private List<T> data;

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        if (this.currentPage <= 0) {
            this.currentPage = 1;
        }
        if (this.pageSize <= 0) {
            this.pageSize = 1;
        }
    }

    public Page(int currentPage, int pageSize, int totalRecord) {
        this(currentPage, pageSize);
        this.totalRecord = totalRecord;
        if (this.totalRecord <= 0) {
            this.totalRecord = 1;
        }
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            return 1;
        }
        return currentPage;
    }

    public int getTotalRecord() {
        if (totalRecord < 0) {
            return 0;
        }
        return totalRecord;
    }

    public int getTotalPage() {
        if (totalRecord <= 0) {
            return 0;
        }
        int size = totalRecord / pageSize;//总条数/每页显示的条数=总页数
        int mod = totalRecord % pageSize;//最后一页的条数
        if (mod != 0) {
            size++;
        }
        totalPage = size;
        return totalPage;
    }

    public int getStartRecord() {
        startRecord = (getCurrentPage() - 1) * pageSize;
        return startRecord;
    }

}
