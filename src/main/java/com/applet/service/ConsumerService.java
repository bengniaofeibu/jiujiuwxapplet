package com.applet.service;


import com.applet.entity.WxPay.WxAppletPayRequest;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "wxappletpay")
public interface ConsumerService {
    @RequestMapping(value = "/pay/wx_xcx_appletpay",method = RequestMethod.GET )
    AppletResult appletPlay(@RequestBody WxAppletPayRequest wxAppletPayRequest,@RequestParam("remoteAddr") String remoteAddr,@RequestParam("session") String session);
}
