package com.applet.model;

import java.util.Date;

public class LoginDailyLog {
    private String userId;

    private Date day;

    private String id;

    private Date addtime;

    private String phoneSystemVersion;

    private String appVersion;

    private Date updateDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getPhoneSystemVersion() {
        return phoneSystemVersion;
    }

    public void setPhoneSystemVersion(String phoneSystemVersion) {
        this.phoneSystemVersion = phoneSystemVersion == null ? null : phoneSystemVersion.trim();
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}