package com.applet.entity.LockRequest;


public class CyclingEndInfoRequest extends BaseLockRequest {

    private String userId;

    private String latitude;

    private String longitude ;

    private Integer jiuMiShowFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getJiuMiShowFlag() {
        return jiuMiShowFlag;
    }

    public void setJiuMiShowFlag(Integer jiuMiShowFlag) {
        this.jiuMiShowFlag = jiuMiShowFlag;
    }
}
