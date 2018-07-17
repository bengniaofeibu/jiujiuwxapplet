package com.applet.service;

import com.applet.entity.store.AutoLoginReq;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jjduiba")
public interface StoreService {

    @RequestMapping(value = "/duiba/autoLogin",method = RequestMethod.POST)
    AppletResult duiba(@RequestBody AutoLoginReq autoLoginReq);

}
