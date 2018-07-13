package com.applet.entity.LockResponse;

import com.applet.model.NyCustomStore;

import java.util.List;

public class CyclingEndInfoResponse extends BaseLockResponse {

    private String cyclingTime;

    private Integer jiuRice;

    private List<NyCustomStore> nyCustomStores;


    public CyclingEndInfoResponse() {
    }

    public CyclingEndInfoResponse(String cyclingTime,Integer jiuRice,List<NyCustomStore> nyCustomStores) {
        this.cyclingTime = cyclingTime;
        this.jiuRice=jiuRice;
        this.nyCustomStores = nyCustomStores;
    }

    public String getCyclingTime() {
        return cyclingTime;
    }

    public void setCyclingTime(String cyclingTime) {
        this.cyclingTime = cyclingTime;
    }

    public Integer getJiuRice() {
        return jiuRice;
    }

    public void setJiuRice(Integer jiuRice) {
        this.jiuRice = jiuRice;
    }

    public List<NyCustomStore> getNyCustomStores() {
        return nyCustomStores;
    }

    public void setNyCustomStores(List<NyCustomStore> nyCustomStores) {
        this.nyCustomStores = nyCustomStores;
    }
}
