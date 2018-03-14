package com.applet.entity.wechatprogram;


//@Table(name = "t_electric_fance_info")
public class ElectricFanceInfo {

    // 反馈ID
    private String id;
    private String latititudeLongitude;
    private Integer sort;
    private Integer fanceNo;
    private String areaName;
    private Integer delFlag;
    private Integer areaId;
    private Integer type;
    private Double latitude;
    private Double longitude;

    public String getLatititudeLongitude() {
        return latititudeLongitude;
    }

    public void setLatititudeLongitude(String latititudeLongitude) {
        this.latititudeLongitude = latititudeLongitude;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getFanceNo() {
        return fanceNo;
    }

    public void setFanceNo(Integer fanceNo) {
        this.fanceNo = fanceNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

