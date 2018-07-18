package com.applet.service;

import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
import com.applet.utils.AppletResult;

public interface HomeService {
    AppletResult getPopup(String cityName);

    UserInfo selectByUserPhone(String phone);

    int updatePhone(UserInfo userInfo);

    UserInfo selectOldPhone(UserInfo oldUser);

    int updateWxUser(WxUserInfo wxUserInfo);
}
