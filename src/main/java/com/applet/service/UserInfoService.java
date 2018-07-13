package com.applet.service;

import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
import com.applet.utils.AppletResult;

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
    UserInfoResponse getUserInfo(String openId,String id,String cityName);


    /**
     * 更新用户登录状态
     * @param phone
     * @return
     */
    void updateUserLoginStatus(String phone,String openId);

    /**
     * 记录用户打开小程序
     * @param userInfo
     */
    AppletResult recordUserOpenXcx(UserInfo userInfo);


    /**
     * 获取用户赳米周榜
     * @param userId
     * @return
     */
    AppletResult getUserJiuMiWeekRankingList(String userId,Integer rankingType);

}
