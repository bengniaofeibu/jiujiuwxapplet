package com.applet.entity.UserInfo;

import com.applet.entity.BaseEntity.BaseResponseEntity;

public class UserInfoResponse extends BaseResponseEntity{



    private String adminId;

    private String phone;

    private Integer status;

    private Integer borrowBicycle;

    private Long ridingTime;

    private Integer loginStatus;

    /** 免押金状态 0:不免 1:免 **/
    private Integer freeDepositStatus;

    private Integer UserBicycleNo;

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBorrowBicycle() {
        return borrowBicycle;
    }

    public void setBorrowBicycle(Integer borrowBicycle) {
        this.borrowBicycle = borrowBicycle;
    }

    public Long getRidingTime() {
        return ridingTime;
    }

    public void setRidingTime(Long ridingTime) {
        this.ridingTime = ridingTime;
    }

    public Integer getFreeDepositStatus() {
        return freeDepositStatus;
    }

    public void setFreeDepositStatus(Integer freeDepositStatus) {
        this.freeDepositStatus = freeDepositStatus;
    }

    public Integer getUserBicycleNo() {
        return UserBicycleNo;
    }

    public void setUserBicycleNo(Integer userBicycleNo) {
        UserBicycleNo = userBicycleNo;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }
}
