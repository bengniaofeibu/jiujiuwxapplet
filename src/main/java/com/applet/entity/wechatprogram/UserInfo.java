package com.applet.entity.wechatprogram;


import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
/**
 * 用户所有信息
 */
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 2354347750872439325L;

    // 用户ID
    private String id;

    // 手机号 唯一
    public String phone;

    // 注册时间
    private Date addTime;

    // 登陆状态 1、未登录 2、登录
    private Integer loginState = 1;

    // 真实姓名
    private String realName;

    // 身份证号
    private String idCardnum;

    // 登陆状态 0 未设置  1 男 2 女
    private Integer gender;

    // 手势密码状态 0、未设置 1、设置并开启 2、设置且关闭
    public Integer guesterState = 0;

    // 登陆时间
    private Date loginTime;

    //积分
    private Integer integral ;

    //信用积分
    private Integer creditScore ;

    //手机借车标志位  0：还车 1：借车 2：预约状态
    private Integer mBorrowBicycle;

    //账户状态（0：手机认证状态 1：押金充值状态 2：实名认证状态 3：正式用户 4：待支付状态）
    private Integer accountStatus;

    //用户等级（0：普通会员 1：1星用户  100:VIP）
    private Integer userLevel;

    // 借车时间
    private Date mBorrowBicycleDate;

    // 押金
    private BigDecimal deposit ;

    // 余额
    private BigDecimal balance ;

    private BigDecimal balanceFree;

    private Timestamp monthCardTime;

    // 手机系统+版本号
    private String mPhoneSystemVersion;

    private String appVersion;

    //城市代码
    private String cityNo;

    //昵称
    private String nickname;

    //头像路径
    private String picurl;

    //国籍
    private String nationality;

    //证件正面
    private String certificatePositive;

    //证件反面
    private String certificateNegative;

    // 会员开通日期
    private Date openDate;

    // 会员有效期
    private Date validateDate;

    //换电瓶状态(0：正常 1：已取车上电池 2:已还电池 3：已取电池 4:已还电池至自行车上 )
    private Integer changBatteryStatus = 0;


    // 状态更新时间
    private Date statusChangeTime;

    // 红包
    private BigDecimal luckyMoney ;

    // 年龄
    public Integer age ;

    // 支付宝账号
    private String alipayAccount;

    //安卓打包渠道
    private String channel;

    //注册城市名
    private String registerCity;

    private String imei;

    private String mac;

    private String idfa;

    private String remarks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardnum() {
        return idCardnum;
    }

    public void setIdCardnum(String idCardnum) {
        this.idCardnum = idCardnum;
    }

    public Integer getGuesterState() {
        return guesterState;
    }

    public void setGuesterState(Integer guesterState) {
        this.guesterState = guesterState;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Integer creditScore) {
        this.creditScore = creditScore;
    }

    public Integer getmBorrowBicycle() {
        return mBorrowBicycle;
    }

    public void setmBorrowBicycle(Integer mBorrowBicycle) {
        this.mBorrowBicycle = mBorrowBicycle;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getmBorrowBicycleDate() {
        return mBorrowBicycleDate;
    }

    public void setmBorrowBicycleDate(Date mBorrowBicycleDate) {
        this.mBorrowBicycleDate = mBorrowBicycleDate;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getmPhoneSystemVersion() {
        return mPhoneSystemVersion;
    }

    public void setmPhoneSystemVersion(String mPhoneSystemVersion) {
        this.mPhoneSystemVersion = mPhoneSystemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCertificatePositive() {
        return certificatePositive;
    }

    public void setCertificatePositive(String certificatePositive) {
        this.certificatePositive = certificatePositive;
    }

    public String getCertificateNegative() {
        return certificateNegative;
    }

    public void setCertificateNegative(String certificateNegative) {
        this.certificateNegative = certificateNegative;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public Integer getChangBatteryStatus() {
        return changBatteryStatus;
    }

    public void setChangBatteryStatus(Integer changBatteryStatus) {
        this.changBatteryStatus = changBatteryStatus;
    }

    public Date getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(Date statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        this.alipayAccount = alipayAccount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getRegisterCity() {
        return registerCity;
    }

    public void setRegisterCity(String registerCity) {
        this.registerCity = registerCity;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public BigDecimal getBalanceFree() {
        return balanceFree;
    }

    public void setBalanceFree(BigDecimal balanceFree) {
        this.balanceFree = balanceFree;
    }

    public Timestamp getMonthCardTime() {
        return monthCardTime;
    }

    public void setMonthCardTime(Timestamp monthCardTime) {
        this.monthCardTime = monthCardTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", addTime=" + addTime +
                ", loginState=" + loginState +
                ", realName='" + realName + '\'' +
                ", idCardnum='" + idCardnum + '\'' +
                ", gender=" + gender +
                ", guesterState=" + guesterState +
                ", loginTime=" + loginTime +
                ", integral=" + integral +
                ", creditScore=" + creditScore +
                ", mBorrowBicycle=" + mBorrowBicycle +
                ", accountStatus=" + accountStatus +
                ", userLevel=" + userLevel +
                ", mBorrowBicycleDate=" + mBorrowBicycleDate +
                ", deposit=" + deposit +
                ", balance=" + balance +
                ", balanceFree=" + balanceFree +
                ", monthCardTime=" + monthCardTime +
                ", mPhoneSystemVersion='" + mPhoneSystemVersion + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", cityNo='" + cityNo + '\'' +
                ", nickname='" + nickname + '\'' +
                ", picurl='" + picurl + '\'' +
                ", nationality='" + nationality + '\'' +
                ", certificatePositive='" + certificatePositive + '\'' +
                ", certificateNegative='" + certificateNegative + '\'' +
                ", openDate=" + openDate +
                ", validateDate=" + validateDate +
                ", changBatteryStatus=" + changBatteryStatus +
                ", statusChangeTime=" + statusChangeTime +
                ", luckyMoney=" + luckyMoney +
                ", age=" + age +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", channel='" + channel + '\'' +
                ", registerCity='" + registerCity + '\'' +
                ", imei='" + imei + '\'' +
                ", mac='" + mac + '\'' +
                ", idfa='" + idfa + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

