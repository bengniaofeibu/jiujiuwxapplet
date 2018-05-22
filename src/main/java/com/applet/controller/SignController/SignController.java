package com.applet.controller.SignController;

import com.applet.controller.BaseController;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.Md5Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignController extends BaseController{


    @Value("${api.sign}")
    private String key;

    //获取APi签名
    @PostMapping(value = "sign")
    @CrossOrigin
    public AppletResult getAPiSign(){
        long timeStrame= System.currentTimeMillis();
        String sign= Md5Util.MD5(timeStrame+key);
        Map signMap=new HashMap<>();
        signMap.put("timeStrame",timeStrame);
        signMap.put("sign",sign);
        return ResultUtil.success(signMap);
    }
}
