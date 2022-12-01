package com.lele.springbootstudy.mybatis;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lele
 * @date: 2022/12/1 8:27
 * @description:
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    // 分页查询开始记录位置
    private transient int begin;
    // 分页查看下结束位置
    private transient int end;
    // 当前页码
    private int pageNo;
    // 每页显示记录数
    private int pageSize;
    // 查询结果总记录数
    private int totalCount;

    private List<T> result;

    /**
     * Instantiates a new Page.
     */
    public Page() {
        this.pageNo = 1;
        this.pageSize = 20;
        this.begin = (this.pageNo - 1) * this.pageSize;
        this.end = this.pageSize;
    }

    /**
     * 构造函数
     * @param pageNo   当前页码
     * @param pageSize 每页显示记录数
     */
    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo < 1 ? 1 : pageNo;
        this.pageSize = pageSize < 1 ? 20 : pageSize;
        this.begin = (this.pageNo - 1) * this.pageSize;
        this.end = this.pageSize;
    }


    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
