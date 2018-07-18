package com.applet.controller.HomeController;

import com.alibaba.fastjson.JSON;
import com.applet.annotation.SystemControllerLog;
import com.applet.entity.Sms;
import com.applet.entity.home.HomeReq;
import com.applet.enums.ResultEnums;
import com.applet.model.UserInfo;
import com.applet.service.HomeService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.GsonTools;
import com.applet.utils.common.PostRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private Sms sms;

    private static final Logger LOGGER= LoggerFactory.getLogger(HomeController.class);

    @SystemControllerLog(funcionExplain = "进入二级弹窗控制层")
    @PostMapping(value = "/popup")
    public AppletResult getPopup(HomeReq homeReq){
        String cityName = homeReq.getCityName();
        return homeService.getPopup(cityName);
    }

    @SystemControllerLog(funcionExplain = "进入修改手机控制层")
    @GetMapping(value = "/changePhone")
    public AppletResult changePhone(@RequestParam("phone") String phone, @RequestParam("captchaNum") String captchaNum,@RequestParam("userId")
                                    String userId){
        try {
            Map<String, Object> m = new HashMap<>();
            m.put("phone", phone);
            m.put("markId", sms.getPrefix());
            m.put("captchaNum", captchaNum);
            String s = PostRequestUtils.httpPostWithJSON(sms.getCheckUrl(), JSON.toJSONString(m));
            Map resultMap = GsonTools.changeGsonToMaps(s);
            LOGGER.debug("验证验证码的返回结果 {}",s);
            if(s.contains("200")){
                UserInfo userInfo = homeService.selectByUserPhone(phone);
                if (userInfo!=null){
                    return ResultUtil.error(ResultEnums.PHONG_USERD);
                }
                UserInfo info = new UserInfo();
                info.setId(userId);
                info.setPhone(phone);
                int i = homeService.updatePhone(info);
                if (i>0){
                    return ResultUtil.success();
                }else {
                    return ResultUtil.error(ResultEnums.SERVER_ERROR);
                }
            }else {
                return ResultUtil.error(resultMap.get("code"),resultMap.get("msg").toString());
            }
        } catch (Exception e) {
            LOGGER.error("ERROR {}",e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
