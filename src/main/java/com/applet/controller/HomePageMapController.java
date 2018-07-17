package com.applet.controller;

import com.applet.entity.BanParkingSpotsReq;
import com.applet.service.BanParkingSpotsService;
import com.applet.utils.AppletResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ban")
public class HomePageMapController extends BaseController{

    @Autowired
    private BanParkingSpotsService banParkingSpotsService;


    /**
     * 获取禁停区和禁行区
     * @param banParkingSpotsReq
     * @return
     */
     @PostMapping(value = "/wx_xcx_querybanparkingspots")
     public AppletResult selectBanParkingSpots(BanParkingSpotsReq banParkingSpotsReq){
         return banParkingSpotsService.selectBanParkingSpots(banParkingSpotsReq);
     }
}
