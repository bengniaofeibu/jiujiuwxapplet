package com.applet.utils;

import com.alibaba.fastjson.JSONObject;
import com.applet.enums.ResultEnums;
import com.applet.exception.SiteException;
import jiujiu.Head.ApiHead;
import jiujiu.Verification.ApiVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 * ----api接口管理类
 *
 * @outhor 张工
 * @create 2018-03-19 21:24
 */
public class ApiManager {
    private static final ThreadLocal<ApiHead> threadLocalHead=new ThreadLocal<>();

    private final static String APP_KEY="012110785149daa6016058fc201e1118";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiManager.class);

    public static ApiHead getApiHead(HttpServletRequest request){

        ApiHead requestHead=new ApiHead();
        requestHead.setVersion(request.getHeader("version"));
        requestHead.setCertType(request.getHeader("certType"));
        requestHead.setCertification(request.getHeader("certification"));
        requestHead.setTimestamp(request.getHeader("timestamp"));
        requestHead.setUserId(request.getHeader("userId"));
        requestHead.setPlat(request.getHeader("plat"));
//        requestHead.setLongitude(request.getHeader("longitude "));
//        requestHead.setLatitude(request.getHeader("latitude "));
        requestHead.setIsTest(request.getIntHeader("isTest"));
//        requestHead.setAppVersion(request.getHeader("appVersion"));
        return requestHead;
    }

    public static ApiHead getApiHead(){
        return threadLocalHead.get();
    }


    public static void valideHeadApi(ApiHead head) throws Exception {
        String valideMsg= ApiVerification.VerificationHandle(head,APP_KEY);
        JSONObject jsonObject=JSONObject.parseObject(valideMsg);
        //参数格式校验失败

        LOGGER.debug("签名验证code {}",jsonObject.getInteger("code"));

        if(jsonObject.getInteger("code")==201){
            SiteException siteException=new
                    SiteException(ResultEnums.RETURN_PARAM_LOST);
            throw siteException;

        }else if(jsonObject.getInteger("code")==202){
            //签名校验失败
            SiteException siteException=new
                    SiteException(ResultEnums.RETURN_PARAM_SIGN);
            throw siteException;
        }
    }
}
