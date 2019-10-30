package com.zk.domain;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PageBean {
    private int page; // 当前页
    private int limit; // 每页显示多少条记录

    // 查询数据库
    private List recordList; // 本页的数据列表
    private int recordCount; // 总记录数

    // 计算
    private int pageCount; // 总页数
    private int beginPageIndex; // 页码列表的开始索引（包含）
    private int endPageIndex; // 页码列表的结束索引（包含）

    public PageBean(){}
    public PageBean(int page, int limit, List recordList, int recordCount, int pageCount, int beginPageIndex, int endPageIndex) {
        this.page = page;
        this.limit = limit;
        this.recordList = recordList;
        this.recordCount = recordCount;
        this.pageCount = pageCount;
        this.beginPageIndex = beginPageIndex;
        this.endPageIndex = endPageIndex;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List getRecordList() {
        return recordList;
    }

    public void setRecordList(List recordList) {
        this.recordList = recordList;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        if(pageCount==0){
            pageCount=(page-1)*limit;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }
}

