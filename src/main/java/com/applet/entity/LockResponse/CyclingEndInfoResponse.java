package com.applet.entity.LockResponse;

import com.applet.model.NyCustomStore;

import java.util.List;

public class CyclingEndInfoResponse extends BaseLockResponse {

    private String cyclingTime;

    private List<NyCustomStore> nyCustomStores;


    public CyclingEndInfoResponse() {
    }

    public CyclingEndInfoResponse(String cyclingTime,List<NyCustomStore> nyCustomStores) {
        this.cyclingTime = cyclingTime;
        this.nyCustomStores = nyCustomStores;
    }

    public String getCyclingTime() {
        return cyclingTime;
    }

    public void setCyclingTime(String cyclingTime) {
        this.cyclingTime = cyclingTime;
    }

    public List<NyCustomStore> getNyCustomStores() {
        return nyCustomStores;
    }

    public void setNyCustomStores(List<NyCustomStore> nyCustomStores) {
        this.nyCustomStores = nyCustomStores;
    }
}
