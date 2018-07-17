package com.applet.entity;

/**
 * Created by Administrator on 2018/6/20.
 */
public class MyJiuMiReq{

    private String userId;

    private Integer currentPage;

    private String plat="wx";

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }
}
