package com.applet.service;

import com.applet.entity.BanParkingSpotsReq;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jjbicyclebike")
public interface BanParkingSpotsService {


    /**
     * 获取禁停区和禁行区
     * @param banParkingSpotsReq
     * @return
     */
    @RequestMapping(value = "/bike/query/banparkingspots",method = RequestMethod.POST)
    AppletResult selectBanParkingSpots(@RequestBody BanParkingSpotsReq banParkingSpotsReq);
}
