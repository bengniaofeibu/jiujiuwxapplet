package com.applet.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.applet.annotation.SystemControllerLog;
import com.applet.entity.Cat;
import com.applet.entity.UserInfo.SessionResponse;
import com.applet.entity.WxApplet;
import com.applet.enums.ResultEnums;
import com.applet.utils.AppletResult;
import com.applet.utils.HttpClient.HttpsUtil;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.Md5Util;
import com.applet.utils.common.RedisUtil;
import com.applet.utils.common.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorized")
public class AuthorizedController {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthorizedController.class);

    private static final String WX_API_URL="https://api.weixin.qq.com/sns/jscode2session?";

    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private WxApplet wxApplet;

    @SystemControllerLog(funcionExplain = "进入微信初始化控制层")
    @GetMapping("/wx_xcx_init")
    public AppletResult login(@RequestParam("js_code")String code){

        try {
            StringBuffer sb = new StringBuffer();
            sb.append(WX_API_URL);
            sb.append("appid=").append(wxApplet.getAppId());
            sb.append("&secret=").append(wxApplet.getAppSecret());
            sb.append("&js_code=").append(code);
            sb.append("&grant_type=authorization_code");
            String s = HttpsUtil.httpMethodGet(sb.toString());
            JSONObject jo = JSON.parseObject(s);
            LOGGER.debug("初始化 JSONObject {}",jo);

            String openId = jo.getString("openid");
            String sessionKey = jo.getString("session_key");


            String uuid = UuidUtil.getUuid();
            String session = Md5Util.MD5(uuid + openId);


            redisUtil.setObj(session,new Cat(openId,sessionKey));
            LOGGER.debug("3rd_session {} 存入缓存 openid {},session_key {}",session,openId,sessionKey);
            return ResultUtil.success(new SessionResponse(session));
        } catch (Exception e) {
            LOGGER.error("ERROR {}",e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}


