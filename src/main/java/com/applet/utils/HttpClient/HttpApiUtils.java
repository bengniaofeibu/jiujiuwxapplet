package com.applet.utils.HttpClient;

import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.XmlOrMapUtils;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class HttpApiUtils {

    private static String recEncoding = "UTF-8";

//    private static final String OPEN_LOCK="http://139.196.194.172:8082/MidComPro/lock?action=OPENLOCK";
    private static final String OPEN_LOCK="http://10.0.180.37/MidComPro/lock?action=OPENLOCK";

//    private static final String GET_BIKE_INFOS="http://139.196.194.172:8084/LockApi/lock?action=GETBIKEINFOS";
    private static final String GET_BIKE_INFOS="http://10.0.180.67/LockApi/lock?action=GETBIKEINFOS";

//    private static final String GET_COUPON_IMAGE_URL="http://139.196.194.172:8088/basicservice/coupon/collection";
    private static final String GET_COUPON_IMAGE_URL="http://10.0.180.120/basicservice/coupon/collection";

    private static final String SMS_OPEN_LOCK_URL = "http://106.14.155.161/SMSComPro/Sms?action=SENDSMSORDER";

    private static final String WX_PAY_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";


    public static String getRecEncoding() {
        return recEncoding;
    }

    public static void setRecEncoding(String recEncoding) {
        HttpApiUtils.recEncoding = recEncoding;
    }

    public static String getBikeInfos(Map params){
        return HttpRequestProxy.doPost(GET_BIKE_INFOS, params , getRecEncoding());
    }

    public static String openLockByGprs(Map params){
        return HttpRequestProxy.doPost(OPEN_LOCK, params , getRecEncoding());
    }

    /**
     * 微信支付请求
     * @param params
     * @return
     */
    public static String sendRequest(String params){
         return HttpsUtil.httpMethodPost(WX_PAY_URL,params);
    }

    public static String openLockBySms(Map params){
        return HttpRequestProxy.doPost(SMS_OPEN_LOCK_URL, params , getRecEncoding());
    }

    /**
     * 获取优惠券图片url
     * @param userId
     * @param cityName
     * @return
     */
    public static String getCouponImageUrl(String userId,String cityName) throws Exception {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("userId",userId);
        paramMap.put("cityName",cityName);
        return  HttpRequestProxy.doPost(GET_COUPON_IMAGE_URL, JSONUtil.toJSONString(paramMap),getRecEncoding());
    }
}
