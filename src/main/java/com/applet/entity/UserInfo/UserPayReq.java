package com.applet.entity.UserInfo;


import java.math.BigDecimal;

public class UserPayReq{

    private String orderNumber;

    private BigDecimal totalAmount;

    private String userPayNumber;

    private String tradeNo;

    private Short payWay;

    /** 订单用途 **/
    private String orderSubject;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserPayNumber() {
        return userPayNumber;
    }

    public void setUserPayNumber(String userPayNumber) {
        this.userPayNumber = userPayNumber;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Short getPayWay() {
        return payWay;
    }

    public void setPayWay(Short payWay) {
        this.payWay = payWay;
    }

    public String getOrderSubject() {
        return orderSubject;
    }

    public void setOrderSubject(String orderSubject) {
        this.orderSubject = orderSubject;
    }
}
