package com.sina.young.jiandandemo1.bean;

import java.util.List;

/**
 * Created by zhaoyang on 16/8/22.
 */
public class RefreshNewsBean {

    /**
     * count_total : 56679
     * pages : 2362
     * count : 24
     * status : ok
     */
    private int count_total;
    private int pages;
    private int count;
    private List<NewsBean> posts;
    private String status;

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPosts(List<NewsBean> posts) {
        this.posts = posts;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount_total() {
        return count_total;
    }

    public int getPages() {
        return pages;
    }

    public int getCount() {
        return count;
    }

    public List<NewsBean> getPosts() {
        return posts;
    }

    public String getStatus() {
        return status;
    }


}
