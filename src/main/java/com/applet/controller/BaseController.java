package com.applet.controller;


import com.applet.entity.Cat;
import com.applet.entity.BaseEntity.BaseRequestEntity;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.enums.ResultEnums;
import com.applet.enums.WxCallBackResultEnums;
import com.applet.mapper.NyCollectionInfoMapper;
import com.applet.mapper.UserInfoMapper;
import com.applet.mapper.WxUserInfoMapper;
import com.applet.model.UserInfo;
import com.applet.service.RidingService;
import com.applet.service.ScavengingUnlockService;
import com.applet.service.UserInfoService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.EncrypUtil;
import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.RedisUtil;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

public class BaseController {


    @Autowired
    protected RedisUtil redisUtil;

    @Autowired
    protected WxUserInfoMapper wxUserInfoMapper;

    @Autowired
    protected RidingService ridingService;

    @Autowired
    protected ScavengingUnlockService scavengingUnlockService;

    @Autowired
    protected UserInfoService userInfoService;

    @Autowired
    protected UserInfoMapper userInfoMapper;

    @Autowired
    protected NyCollectionInfoMapper nyCollectionInfoMapper;


    /**
     * 获取授权信息
     *
     * @param session
     * @return
     */
    protected Cat getAuthInfo(String session) {
        Object obj = redisUtil.getValueObj(session);
        return JSONUtil.parseObject(JSONUtil.toJSONString(obj), Cat.class);
    }


    /**
     * 解密小程序返回的数据并封装成实体
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     */
    protected JSONObject encryptedDataToObject(String encryptedData, String sessionKey, String iv) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
        byte[] resultByte = EncrypUtil.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            String userInfo = new String(resultByte, "UTF-8");
            return new JSONObject(userInfo);
        }
        return null;
    }


    /**
     * 判断用户是否存在
     *
     * @param userMobile
     * @return
     */
    protected AppletResult isUserRegistered(String userMobile) {
        String userId = wxUserInfoMapper.selectUserIdByMobile(userMobile);
        if (userId != null) {
            return ResultUtil.success(userId);
        }
        return null;
    }


    /**
     * 判断该微信是否已经绑定过手机号
     * @param openId
     * @return
     */
    protected boolean getCountByOpenId(String openId) {
        return wxUserInfoMapper.selectCountByOpenId(openId) == 0;
    }

    /**
     * 获取用户信息通过openId
     *
     * @return
     */
    protected UserInfoResponse getUserInfoByopenId(String openId, String userId, String cityName) {
        UserInfoResponse userInfo = userInfoService.getUserInfo(openId, userId, cityName);
        userInfo.setAdminId(userId);
        return userInfo;
    }

    /**
     * 通过用户id获取用户信息
     *
     * @return
     */
    protected UserInfo getUserInfoByUserId(String userId) {
        return userInfoMapper.selectUserInfoById(userId);
    }
}
