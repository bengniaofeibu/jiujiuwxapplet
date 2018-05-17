package com.applet.entity.LockRequest;


import java.util.Map;

public class GeoCacheReq {


    private String key;

    private String startName;

    private String endName;

    private String[] nameArray;

    private Map<String,Object> pointMap;

    private Double lat;

    private Double lng;

    /**  排序方式  0  升序  1 倒序 **/
    private Integer directionType=0;

    /**  距离单位 0 米 1 尺 2 公里 3 英里 **/
    private Integer distanceType=0;

    private long distance;

    private int limit;

    private long expireTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String[] getNameArray() {
        return nameArray;
    }

    public void setNameArray(String[] nameArray) {
        this.nameArray = nameArray;
    }

    public Map<String, Object> getPointMap() {
        return pointMap;
    }

    public void setPointMap(Map<String, Object> pointMap) {
        this.pointMap = pointMap;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Integer getDirectionType() {
        return directionType;
    }

    public void setDirectionType(Integer directionType) {
        this.directionType = directionType;
    }

    public Integer getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(Integer distanceType) {
        this.distanceType = distanceType;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

}
