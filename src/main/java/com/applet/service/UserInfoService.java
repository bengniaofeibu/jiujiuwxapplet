package com.applet.service;

import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;

public interface UserInfoService {

    /**
     * 添加注册信息
     * @param userInfo
     * @param wxUserInfo
     */
    UserInfoResponse addRegisterUser(UserInfo userInfo, WxUserInfo wxUserInfo);


    /**
     *  获取用户信息
     * @param id
     * @return
     */
    UserInfoResponse getUserInfo(String id);
}
