package com.applet.entity.BaseEntity;



import java.io.Serializable;

public abstract class BaseRequestEntity implements  Serializable{


    private static final long serialVersionUID = 8360101072462967795L;
    /**
     * 签名
     **/
    public String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
