package com.applet.service;

import com.applet.entity.UserInfo.UserPayReq;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jjbicyclepay")
public interface UserFinePay {

    @RequestMapping(value = "/pay/userfinepay",method = RequestMethod.POST)
    AppletResult userFinePay(@RequestBody UserPayReq userPayReq);
}
