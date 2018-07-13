package com.applet.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserInfo {
    private String id;

    private Integer accountStatus;

    private Date addTime;

    private String appVersion;

    private String certificateNegative;

    private String certificatePositive;

    private Integer changeBatteryStatus;

    private String cityNo;

    private Integer creditScore;

    private BigDecimal deposit;

    private Integer guesterState;

    private String idCardnum;

    private Integer integral;

    private Integer loginState;

    private Date loginTime;

    private Integer mBorrowBicycle;

    private Date mBorrowBicycleDate;

    private String mPhoneSystemVersion;

    private String nationality;

    private String nickname;

    private Date openDate;

    private String phone;

    private String picurl;

    private String realName;

    private Date statusChangeTime;

    private Integer userLevel;

    private Date validateDate;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String remarks;

    private String delFlag;

    private BigDecimal balance;

    private BigDecimal balanceFree;

    private Date monthCardTime;

    private Integer gender;

    private BigDecimal luckyMoney;

    private Integer age;

    private String alipayAccount;

    private String phoneProvince;

    private String phoneCity;

    private String channel;

    private String registerCity;

    private String imei;

    private String mac;

    private String idfa;

    private Integer userSource;

    public UserInfo(){

    }

    public UserInfo(String id,Integer integral){
        this.id=id;
        this.integral=integral;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getCertificateNegative() {
        return certificateNegative;
    }

    public void setCertificateNegative(String certificateNegative) {
        this.certificateNegative = certificateNegative == null ? null : certificateNegative.trim();
    }

    public String getCertificatePositive() {
        return certificatePositive;
    }

    public void setCertificatePositive(String certificatePositive) {
        this.certificatePositive = certificatePositive == null ? null : certificatePositive.trim();
    }

    public Integer getChangeBatteryStatus() {
        return changeBatteryStatus;
    }

    public void setChangeBatteryStatus(Integer changeBatteryStatus) {
        this.changeBatteryStatus = changeBatteryStatus;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getGuesterState() {
        return guesterState;
    }

    public void setGuesterState(Integer guesterState) {
        this.guesterState = guesterState;
    }

    public String getIdCardnum() {
        return idCardnum;
    }

    public void setIdCardnum(String idCardnum) {
        this.idCardnum = idCardnum == null ? null : idCardnum.trim();
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getmBorrowBicycle() {
        return mBorrowBicycle;
    }

    public void setmBorrowBicycle(Integer mBorrowBicycle) {
        this.mBorrowBicycle = mBorrowBicycle;
    }

    public Date getmBorrowBicycleDate() {
        return mBorrowBicycleDate;
    }

    public void setmBorrowBicycleDate(Date mBorrowBicycleDate) {
        this.mBorrowBicycleDate = mBorrowBicycleDate;
    }

    public String getmPhoneSystemVersion() {
        return mPhoneSystemVersion;
    }

    public void setmPhoneSystemVersion(String mPhoneSystemVersion) {
        this.mPhoneSystemVersion = mPhoneSystemVersion == null ? null : mPhoneSystemVersion.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Date getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Date statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalanceFree() {
        return balanceFree;
    }

    public void setBalanceFree(BigDecimal balanceFree) {
        this.balanceFree = balanceFree;
    }

    public Date getMonthCardTime() {
        return monthCardTime;
    }

    public void setMonthCardTime(Date monthCardTime) {
        this.monthCardTime = monthCardTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public BigDecimal getLuckyMoney() {
        return luckyMoney;
    }

    public void setLuckyMoney(BigDecimal luckyMoney) {
        this.luckyMoney = luckyMoney;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public String getPhoneProvince() {
        return phoneProvince;
    }

    public void setPhoneProvince(String phoneProvince) {
        this.phoneProvince = phoneProvince == null ? null : phoneProvince.trim();
    }

    public String getPhoneCity() {
        return phoneCity;
    }

    public void setPhoneCity(String phoneCity) {
        this.phoneCity = phoneCity == null ? null : phoneCity.trim();
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity == null ? null : registerCity.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa == null ? null : idfa.trim();
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }
}