package com.applet.controller;

import com.applet.annotation.SystemControllerLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/protocol")
public class RegisterController {


    @SystemControllerLog(funcionExplain = "进入获取服务协议控制层")
    @GetMapping(value = "/wx_xcx_sp")
    public String getRegisterProtocol(){

        return "register-protocol";
    }
}
