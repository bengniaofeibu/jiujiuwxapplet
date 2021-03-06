package com.applet.service;

import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
import com.applet.utils.AppletResult;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * 记录用户unionId
     * @param wxUserInfo
     * @return
     */
    AppletResult recordUserUnionId(WxUserInfo wxUserInfo);


    String getWeiXinPublicFollowOrCancel(HttpServletRequest request)  throws Exception;

}
