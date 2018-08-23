package com.applet.controller;

import com.alibaba.fastjson.JSON;
import com.applet.annotation.SystemControllerLog;
import com.applet.entity.Sms;
import com.applet.enums.ResultEnums;
import com.applet.model.UserInfo;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.GsonTools;
import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.PostRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms")
public class SMSController{

    private static final Logger LOGGER= LoggerFactory.getLogger(SMSController.class);

    private static final Integer TYPE = 1;


    @Autowired
    private Sms sms;


    @SystemControllerLog(funcionExplain = "ELK测试")
    @PostMapping("/logstachTest")
    public String logstachTest(@RequestBody UserInfo userInfo){

        LOGGER.info(" info {}","这是一个info日志");
        LOGGER.debug(" debug {}","这是一个debug日志");
        LOGGER.error(" error {}","这是一个error日志");
        return "ok";
    }

    @GetMapping("/wx_xcx_cat")
    @SystemControllerLog(funcionExplain = "进入发送验证码控制层")
    public AppletResult get(@RequestParam("phone") String phone) {

        try {
            Map<String, Object> m = new HashMap<>();
            m.put("phone", phone);
            m.put("markId", sms.getPrefix());
            m.put("smsType", TYPE);

            String s = PostRequestUtils.httpPostWithJSON(sms.getUrl(), JSON.toJSONString(m));
            LOGGER.debug("发送短信的返回结果 {}",s);
            return ResultUtil.success();
        } catch (Exception e) {
            LOGGER.error("ERROR {}",e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }

    @GetMapping("/wx_xcx_fish")
    @SystemControllerLog(funcionExplain = "进入验证验证码控制层")
    public AppletResult check(@RequestParam("phone") String phone, @RequestParam("captchaNum") String captchaNum) {
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("phone", phone);
            m.put("markId", sms.getPrefix());
            m.put("captchaNum", captchaNum);
            String s = PostRequestUtils.httpPostWithJSON(sms.getCheckUrl(), JSON.toJSONString(m));
            Map resultMap = GsonTools.changeGsonToMaps(s);
            LOGGER.debug("验证验证码的返回结果 {}",s);
            if(s.contains("200")){
                return ResultUtil.success();
            }else {
                return ResultUtil.error(resultMap.get("code"),resultMap.get("msg").toString());
            }
        } catch (Exception e) {
            LOGGER.error("ERROR {}",e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
