package com.applet.model;

import java.util.Date;

public class JiumiLog extends BaseModel{

    private static String DEFAULT_NIKE_NAME="99宝贝";

    public static String DEFAULT_USER_PICURL="https://jjdc-client.oss-cn-shanghai.aliyuncs.com/appIcon/defaultHead.png";

    private Long id;

    private String userId;

    private Integer jmType;

    private Long incValue;

    private String nikeName=DEFAULT_NIKE_NAME;

    private String picurl=DEFAULT_USER_PICURL;

    private Integer jiuSum=0;

    private Long beforeIncValue;

    private String objId;

    private Integer delFlag;

    private String jmDesc;

    private String remarks;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;


    public JiumiLog() {
    }

    public JiumiLog(String userId, Integer jmType, Long incValue, Integer delFlag, String jmDesc) {
        this.userId = userId;
        this.jmType = jmType;
        this.incValue = incValue;
        this.delFlag = delFlag;
        this.jmDesc = jmDesc;
    }

    public JiumiLog(String userId, Integer jmType, Long incValue, String objId, Integer delFlag, String jmDesc) {
        this.userId = userId;
        this.jmType = jmType;
        this.incValue = incValue;
        this.objId = objId;
        this.delFlag = delFlag;
        this.jmDesc = jmDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getJmType() {
        return jmType;
    }

    public void setJmType(Integer jmType) {
        this.jmType = jmType;
    }

    public Long getIncValue() {
        return incValue;
    }

    public void setIncValue(Long incValue) {
        this.incValue = incValue;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Integer getJiuSum() {
        return jiuSum;
    }

    public void setJiuSum(Integer jiuSum) {
        this.jiuSum = jiuSum;
    }

    public Long getBeforeIncValue() {
        return beforeIncValue;
    }

    public void setBeforeIncValue(Long beforeIncValue) {
        this.beforeIncValue = beforeIncValue;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId == null ? null : objId.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getJmDesc() {
        return jmDesc;
    }

    public void setJmDesc(String jmDesc) {
        this.jmDesc = jmDesc == null ? null : jmDesc.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
}