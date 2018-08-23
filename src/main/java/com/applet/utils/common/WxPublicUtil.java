package com.applet.utils.common;

import com.alibaba.fastjson.JSONObject;
import com.applet.entity.Wx.*;
import com.applet.model.WxPublicInfo;
import com.applet.utils.HttpClient.HttpApiUtils;
import com.applet.utils.HttpClient.HttpRequestProxy;
import com.github.wxpay.sdk.WXPayConstants;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public final class WxPublicUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpApiUtils.class);

    @Autowired
    private RedisUtil redisUtil;

    private static final WxPublicUserInfo DEFAULT_USER_INFO = new WxPublicUserInfo(0);

    private static final DataValue FIRST_DATA_VALUE = new DataValue(DataValue.FIRST_DATA);

    private static final DataValue JIUMI_REMARK_DATA_VALUE = new DataValue(DataValue.JIU_MI_REMARK_DES);


    /**
     * 获取微信token
     *
     * @return
     */
    public String setAndGetWeiXinToken() {

        String accessToken = redisUtil.getValuesStr(WxConstant.APP_ID);

        if (!StringUtils.isBlank(accessToken)) return accessToken;

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("grant_type", "client_credential");
        reqMap.put("appid", WxConstant.APP_ID);
        reqMap.put("secret", WxConstant.SECRET);

        String result = HttpRequestProxy.doGet(WxConstant.GET_TOKEN, reqMap,HttpRequestProxy.getRequestEncoding());
        LOGGER.debug("获取微信toke结果 {}", result);

        if (StringUtils.isBlank(result)) {
            LOGGER.debug("请求微信toke失败");
            return null;
        }

        WxToken wxToken = JSONUtil.parseObject(result, WxToken.class);

        if (wxToken != null) {

            if (!StringUtils.isBlank(wxToken.getAccess_token())) {

                accessToken = wxToken.getAccess_token();

                redisUtil.setStringAndExpire(WxConstant.APP_ID, wxToken.getAccess_token(), wxToken.getExpires_in());
            } else {

                LOGGER.debug("请求微信toke错误 errcode {} errmsg {} ", wxToken.getErrcode(), wxToken.getErrmsg());

            }
        }
        return accessToken;
    }

    /**
     * 根据openId获取微信用户信息
     *
     * @param openId
     * @return
     */
    public WxPublicUserInfo getWeiXinPublicUserInfo(String openId) {

        //获取accessToken
        String accessToken = setAndGetWeiXinToken();

        if (StringUtils.isBlank(accessToken)) {
            return DEFAULT_USER_INFO;
        }

        Map<String, String> reqMap = new HashMap<>();
        reqMap.put("access_token", accessToken);
        reqMap.put("openid", openId);
        reqMap.put("lang", "zh_CN");
        String result = HttpRequestProxy.doGet(WxConstant.GET_USER_INFO, reqMap, HttpRequestProxy.getRequestEncoding());
        LOGGER.debug("获取微信用户信息 {}", result);

        if (StringUtils.isBlank(result)) {
            LOGGER.debug("请求微信用户信息失败");
            return DEFAULT_USER_INFO;
        }

        WxPublicUserInfo userInfo = JSONUtil.parseObject(result, WxPublicUserInfo.class);
        LOGGER.debug("wxpublicuserinfo {}",userInfo);

        if (userInfo != null) {

            if (StringUtils.isBlank(userInfo.getErrmsg())) {

                return userInfo;

            } else {

                LOGGER.debug("请求微信用户信息错误 errcode {} errmsg {} ", userInfo.getErrcode(), userInfo.getErrmsg());
            }

        }
        return Optional.ofNullable(userInfo).orElse(DEFAULT_USER_INFO);
    }


    /**
     * 发送领取赳米模板
     * @param wxPublicInfo
     * @param templateId
     */
    public  void sendWxTemplate(WxPublicInfo wxPublicInfo, String templateId){

        //获取accessToken
        String accessToken = setAndGetWeiXinToken();

        TemplateDate templateDate = new TemplateDate.Build(FIRST_DATA_VALUE,JIUMI_REMARK_DATA_VALUE)
                .keyword1(new DataValue(wxPublicInfo.getNickName())).build();

        WxPublicTemplate wxPublicTemplate = new WxPublicTemplate(wxPublicInfo.getOpenId(),templateId,templateDate);

        String result = HttpRequestProxy.doPost(WxConstant.SEND_WX_TEMPLATE+accessToken,JSONUtil.toJSONString(wxPublicTemplate),HttpRequestProxy.getRequestEncoding());
        LOGGER.debug("微信发送模板返回结果 {}",result);
    }

    /**
     * 发送领取赳米模板
     * @param wxPublicInfo
     */
    public void sendWxTest(WxPublicInfo wxPublicInfo){

        //获取accessToken
        String accessToken = setAndGetWeiXinToken();

        String result = HttpRequestProxy.doPost(WxConstant.SEND_WX_TEST+accessToken,JSONUtil.toJSONString(new WxPublicText(wxPublicInfo.getOpenId())),HttpRequestProxy.getRequestEncoding());
        LOGGER.debug("微信发送模板返回结果 {}",result);
    }
}
