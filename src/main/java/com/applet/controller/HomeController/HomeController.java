package com.applet.controller.HomeController;

import com.alibaba.fastjson.JSON;
import com.applet.annotation.SystemControllerLog;
import com.applet.entity.Sms;
import com.applet.entity.home.HomeReq;
import com.applet.enums.ResultEnums;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
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
    public AppletResult getPopup(@RequestBody HomeReq homeReq){
        String cityName = homeReq.getCityName();
        return homeService.getPopup(cityName);
    }

    @SystemControllerLog(funcionExplain = "进入修改手机控制层")
    @GetMapping(value = "/changePhone")
    public AppletResult changePhone(@RequestParam("oldPhone") String oldPhone,@RequestParam("newPhone") String newPhone,
                                    @RequestParam("captchaNum") String captchaNum,@RequestParam("userId") String userId){
        try {
            UserInfo oldUser = new UserInfo();
            oldUser.setId(userId);
            oldUser.setPhone(oldPhone);
            UserInfo checkUser = homeService.selectOldPhone(oldUser);
            if (checkUser==null){
                return ResultUtil.error(ResultEnums.NOT_YOUR_PHONE);
            }
            Map<String, Object> m = new HashMap<>();
            m.put("phone", newPhone);
            m.put("markId", sms.getPrefix());
            m.put("captchaNum", captchaNum);
            String s = PostRequestUtils.httpPostWithJSON(sms.getCheckUrl(), JSON.toJSONString(m));
            Map resultMap = GsonTools.changeGsonToMaps(s);
            LOGGER.debug("验证验证码的返回结果 {}",s);
            if(s.contains("200")){
                UserInfo userInfo = homeService.selectByUserPhone(newPhone);
                if (userInfo!=null){
                    return ResultUtil.error(ResultEnums.PHONG_USERD);
                }
                UserInfo info = new UserInfo();
                info.setId(userId);
                info.setPhone(newPhone);
                int i = homeService.updatePhone(info);
                WxUserInfo wxUserInfo = new WxUserInfo();
                wxUserInfo.setUserId(userId);
                wxUserInfo.setUserMobile(newPhone);
                int i1 = homeService.updateWxUser(wxUserInfo);
                if (i>0&&i1>0){
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
