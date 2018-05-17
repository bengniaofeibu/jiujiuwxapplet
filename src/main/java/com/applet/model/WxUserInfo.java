package com.applet.model;

import java.util.Date;

public class WxUserInfo {
    private Long id;

    private String openId;

    private String userId;

    private String userName="";

    private String userMobile;

    private String useridHash="";

    private Integer registFlag;

    private Integer depositFlag;

    private Long depositFee;

    private String transactionId;

    private Integer loginStatus;

    private Date addTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUseridHash() {
        return useridHash;
    }

    public void setUseridHash(String useridHash) {
        this.useridHash = useridHash == null ? null : useridHash.trim();
    }

    public Integer getRegistFlag() {
        return registFlag;
    }

    public void setRegistFlag(Integer registFlag) {
        this.registFlag = registFlag;
    }

    public Integer getDepositFlag() {
        return depositFlag;
    }

    public void setDepositFlag(Integer depositFlag) {
        this.depositFlag = depositFlag;
    }

    public Long getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(Long depositFee) {
        this.depositFee = depositFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}