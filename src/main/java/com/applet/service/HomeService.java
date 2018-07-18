package com.applet.service;

import com.applet.model.BicycleWxUserInfo;
import com.applet.model.UserInfo;
import com.applet.utils.AppletResult;

public interface HomeService {
    AppletResult getPopup(String cityName);

    UserInfo selectByUserPhone(String phone);

    int updatePhone(UserInfo userInfo);

    UserInfo selectOldPhone(UserInfo oldUser);

    int updateWxUser(BicycleWxUserInfo bicycleWxUserInfo);
}
