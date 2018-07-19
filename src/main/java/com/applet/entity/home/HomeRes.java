package com.applet.entity.home;

public class HomeRes {

    private Integer id;

    //显示的话术
    private String displayWords;

    private String picUrl;

    private String actionUrl;

    private Integer actionType;

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayWords() {
        return displayWords;
    }

    public void setDisplayWords(String displayWords) {
        this.displayWords = displayWords;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
}
