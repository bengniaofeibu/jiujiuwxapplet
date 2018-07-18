package com.applet.entity;


/**
 * Created by Administrator on 2018/6/27.
 */
public class AcquireJiuMiReq{

    //获取赳米任务场景类型
    private Integer type;

    //获取赳米类型
    private Integer jmType;

    //操作对象
    private String objId;

    //骑行分钟
    private Integer minute;

    //用户id
    private String userId;

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public Integer getJmType() {
        return jmType;
    }

    public void setJmType(Integer jmType) {
        this.jmType = jmType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
