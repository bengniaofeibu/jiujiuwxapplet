package com.applet.entity.UserInfo;


public class WxUserInfoReq {

    private String phone;

    private String userId;

    private String longitude;

    private String latitude;

    private String openid;

    private String nickname;

    private String unionid;

    private String headimgurl;

    private String cityName;

    private Integer count;

    private String fenceId;

    private String bicycleNo;

    private String transId;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getFenceId() {
        return fenceId;
    }

    public void setFenceId(String fenceId) {
        this.fenceId = fenceId;
    }

    public String getBicycleNo() {
        return bicycleNo;
    }

    public void setBicycleNo(String bicycleNo) {
        this.bicycleNo = bicycleNo;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
