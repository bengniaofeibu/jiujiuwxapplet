package com.applet.utils;

import com.applet.entity.BaseEntity.BaseRequestEntity;
import com.applet.utils.HttpClient.HttpApiUtils;
import com.applet.utils.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public final class WxAppletServiceUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxAppletServiceUtil.class);

    private static final SimpleDateFormat DATA_FOMAT = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 腾讯财付通商户号(微信支付商户号)
     **/
    private static final String MCH_ID = "1461111202";

    public static final String APP_ID="wxd1cfbb46ce504c75";

    /**
     * 小程序的key
     **/
    private static final String KEY = "43DBE8BC489BDC916DE00807BCB4B7D0";

    private static final String TRADE_TYPE="JSAPI";




    private static final Map<String, Object> BASE_PARAM = new HashMap<>();

    private static final List<String> NOT_SIGN_VALUE = Arrays.asList("sign");


    static {
        BASE_PARAM.put("appid",APP_ID);
    }


    /**
     * 生成签名
     *
     * @return
     */
    public static String generateSign(Map<String, Object> valueMap) {
        if (!valueMap.containsValue(APP_ID)){
            valueMap.putAll(BASE_PARAM);
        }
        //格式话签名参数
        String params = RequestValueUtils.formatParameters(valueMap, false);
        StringBuffer buffer = new StringBuffer(params);
        buffer.append("&").append("key").append("=").append(KEY);
        System.out.println(buffer.toString());
        return Md5Util.MD5(buffer.toString());
    }


    /**
     * 生成验证签名的map
     *
     * @return
     */
    private static Map<String, Object> generateSignMap(BaseRequestEntity baseWcxRequest) {
        Map<String, Object> map = new HashMap<>();
        Class<? extends BaseRequestEntity> tClass = baseWcxRequest.getClass();
        try {
            for (Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object o = field.get(baseWcxRequest);
                if (o != null) {
                    map.put(field.getName(), o);
                }
            }

            Class<?> superclass = tClass.getSuperclass();
            for (Field field : superclass.getDeclaredFields()) {
                if (!NOT_SIGN_VALUE.contains(field.getName())) {
                    field.setAccessible(true);
                    Object o = field.get(baseWcxRequest);
                    if (o != null) {
                        map.put(field.getName(), o);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 验证签名
     *
     * @param baseWcxRequest
     */
    public static boolean verificationSign(BaseRequestEntity baseWcxRequest) {
        String sign = generateSign(generateSignMap(baseWcxRequest));
        return baseWcxRequest.getSign().equals(sign);
    }


    /**
     * 对所以参数进行Encoder
     *
     * @param paramMap
     * @return
     */
    public static Map<String, Object> getParamMapToEncoder(Map<String, Object> paramMap, boolean res) {
        Map<String, Object> map = new LinkedHashMap<>();
        String valueEncoder;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                if (res) {
                    //如果为true进行编码
                    valueEncoder = BaseUtil.getURLEncoder(value.toString());
                } else {
                    //如果为false进行解码
                    valueEncoder = BaseUtil.getURLDecode(value.toString());
                }
                map.put(entry.getKey(), valueEncoder);
            }
        }
        return map;
    }

    /**
     * 把放回值转成对应的实体bean
     *
     * @param result
     * @param tClass
     * @param <T>
     * @return
     */
    private static  <T> T getResponseToObject(String result, Class<T> tClass) {
        return null;
    }

    public static void main(String[] args) throws Exception {

    }
}
