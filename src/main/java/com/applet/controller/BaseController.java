package com.applet.controller;


import com.applet.entity.Cat;
import com.applet.entity.BaseEntity.BaseRequestEntity;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.enums.ResultEnums;
import com.applet.enums.WxCallBackResultEnums;
import com.applet.mapper.UserInfoMapper;
import com.applet.mapper.WxUserInfoMapper;
import com.applet.service.RidingService;
import com.applet.service.ScavengingUnlockService;
import com.applet.service.UserInfoService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.EncrypUtil;
import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.RedisUtil;
import com.applet.utils.common.XmlOrMapUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * 获取授权信息
     * @param session
     * @return
     */
    protected Cat getAuthInfo(String session){
     Object obj = redisUtil.getValueObj(session);
      return JSONUtil.parseObject(JSONUtil.toJSONString(obj),Cat.class);
    }


    /**
     * 解密小程序返回的数据并封装成实体
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @param tClass
     * @param <T>
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws UnsupportedEncodingException
     */
    protected  <T> T encryptedDataToObject(String encryptedData,String sessionKey,String iv,Class<T> tClass) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
        byte[] resultByte = EncrypUtil.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            String userInfo = new String(resultByte, "UTF-8");
            return JSONUtil.parseObject(userInfo, tClass);
        }
           return null;
    }


    /**
     * 判断用法是否存在
     * @param userMobile
     * @return
     */
    protected AppletResult isUserRegistered(String userMobile){
        Integer count = wxUserInfoMapper.selectNumByMobile(userMobile);
        if (count>0){
            return ResultUtil.error(ResultEnums.USER_ALREADY_EXIST);
        }
        return null;
    }


    /**
     * 获取用户信息通过openId
     *
     * @param userMobile
     * @return
     */
    protected UserInfoResponse getUserInfoByopenId(String openId,String userMobile,String cityName) {
        String userId = wxUserInfoMapper.selectUserIdByMobile(userMobile);
        UserInfoResponse userInfo = userInfoService.getUserInfo(openId,userId,cityName);
        userInfo.setAdminId(userId);
        return userInfo;
    }
}
