package com.applet.model;

import java.util.Date;

public class FineDetail {
    private String id;

    private String bicycleNo;

    private String userId;

    private String realName;

    private String phone;

    private String fence;

    private Long fineMoney;

    private Integer fineType;

    private Long fineCredit;

    private Integer noparkTimes;

    private String rechargeId;

    private String transId;

    private Integer delFlag;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBicycleNo() {
        return bicycleNo;
    }

    public void setBicycleNo(String bicycleNo) {
        this.bicycleNo = bicycleNo == null ? null : bicycleNo.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFence() {
        return fence;
    }

    public void setFence(String fence) {
        this.fence = fence == null ? null : fence.trim();
    }

    public Long getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(Long fineMoney) {
        this.fineMoney = fineMoney;
    }

    public Integer getFineType() {
        return fineType;
    }

    public void setFineType(Integer fineType) {
        this.fineType = fineType;
    }

    public Long getFineCredit() {
        return fineCredit;
    }

    public void setFineCredit(Long fineCredit) {
        this.fineCredit = fineCredit;
    }

    public Integer getNoparkTimes() {
        return noparkTimes;
    }

    public void setNoparkTimes(Integer noparkTimes) {
        this.noparkTimes = noparkTimes;
    }

    public String getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(String rechargeId) {
        this.rechargeId = rechargeId == null ? null : rechargeId.trim();
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId == null ? null : transId.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}