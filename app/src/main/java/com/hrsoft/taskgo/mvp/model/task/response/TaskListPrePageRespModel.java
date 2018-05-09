package com.hrsoft.taskgo.mvp.model.task.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 分页响应体
 *
 * @author FanHongyu.
 * @since 18/5/8 16:40.
 * email fanhongyu@hrsoft.net.
 */

public class TaskListPrePageRespModel<T> {

    /**
     * 当前页码
     */
    @SerializedName("current_page")
    private int currentPage;

    /**
     * 数据列表
     */
    private List<TasListRespModel<T>> data;


    /**
     * 第一页地址
     */
    @SerializedName("first_page_url")
    private String firstPageUrl;


    private String from;


    /**
     * 最后一页
     */
    @SerializedName("last_page")
    private int lastPage;

    /**
     * 最后一页
     */
    @SerializedName("last_page_url")
    private String lastPageUrl;


    /**
     * 下一页地址
     */
    @SerializedName("next_page_url")
    private String nextPageUrl;


    /**
     * 原地址
     */
    private String path;


    /**
     * 前一页
     */
    @SerializedName("per_page")
    private int perPage;

    /**
     * 前一页地址
     */
    @SerializedName("prev_page_url")
    private String prevPageUrl;

    /**
     * 作用未知
     */
    private String to;

    /**
     * 总页数
     */
    private int total;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<TasListRespModel<T>> getData() {
        return data;
    }

    public void setData(List<TasListRespModel<T>> data) {
        this.data = data;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
