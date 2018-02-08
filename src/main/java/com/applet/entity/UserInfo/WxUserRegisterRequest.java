package com.applet.entity.UserInfo;

import com.applet.entity.BaseEntity.BaseRequestEntity;

public class WxUserRegisterRequest extends BaseRequestEntity{

    private String rawData;

    private String signature;

    private String detailedEncryptedData;

    private String generalEncryptedData;

    private String iv;

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDetailedEncryptedData() {
        return detailedEncryptedData;
    }

    public void setDetailedEncryptedData(String detailedEncryptedData) {
        this.detailedEncryptedData = detailedEncryptedData;
    }

    public String getGeneralEncryptedData() {
        return generalEncryptedData;
    }

    public void setGeneralEncryptedData(String generalEncryptedData) {
        this.generalEncryptedData = generalEncryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
