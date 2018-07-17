package com.applet.controller.StoreController;

import com.applet.annotation.SystemControllerLog;
import com.applet.entity.store.AutoLoginReq;
import com.applet.entity.store.DuibaReq;
import com.applet.service.StoreService;
import com.applet.utils.AppletResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;


    @SystemControllerLog(funcionExplain = "进入小程序兑吧控制层")
    @PostMapping(value = "/duiba")
    public AppletResult duiba(@RequestBody DuibaReq duibaReq){
        String adminId = duibaReq.getAdminId();
        AutoLoginReq req = new AutoLoginReq();
        req.setUserId(adminId);
        return storeService.duiba(req);
    }
}
