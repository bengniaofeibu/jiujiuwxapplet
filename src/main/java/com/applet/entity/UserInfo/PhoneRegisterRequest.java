package com.applet.entity.UserInfo;

import com.applet.entity.BaseEntity.BaseRequestEntity;

public class PhoneRegisterRequest extends BaseRequestEntity {


    private String phone;

    private String cityName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
