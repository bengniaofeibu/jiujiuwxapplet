package com.applet.entity.LockRequest;

public class ScaveningUnlockRequest extends BaseLockRequest{

    private String longitude;

    private String latitude;

    private String id;

    private String barcode;

    /** 免押金状态 0:不免 1:免 **/
    private Integer freeDepositStatus;

    private Integer jiuMiShowFlag;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getFreeDepositStatus() {
        return freeDepositStatus;
    }

    public void setFreeDepositStatus(Integer freeDepositStatus) {
        this.freeDepositStatus = freeDepositStatus;
    }

    public Integer getJiuMiShowFlag() {
        return jiuMiShowFlag;
    }

    public void setJiuMiShowFlag(Integer jiuMiShowFlag) {
        this.jiuMiShowFlag = jiuMiShowFlag;
    }
}
