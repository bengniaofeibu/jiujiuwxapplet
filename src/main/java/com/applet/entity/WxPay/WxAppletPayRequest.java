package com.applet.entity.WxPay;

import com.applet.entity.BaseEntity.BaseRequestEntity;

import java.math.BigDecimal;

public class WxAppletPayRequest extends BaseRequestEntity {

    private final long serialVersionUID = 8360101072462967795L;

    /**
     * 金额
     **/
    private String amount;


    /**
     * 订单名称
     **/
    private String modeName;

    /**
     * 用户IP地址
     */
    private String userHost;


    /**
     * 用户opendId
     */
    private String openId;

    /**
     * 用户id
     */
    private String adminId;


    public WxAppletPayRequest() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getUserHost() {
        return userHost;
    }

    public void setUserHost(String userHost) {
        this.userHost = userHost;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
