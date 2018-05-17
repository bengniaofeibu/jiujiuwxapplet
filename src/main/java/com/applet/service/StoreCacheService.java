package com.applet.service;

import com.applet.entity.LockRequest.GeoCacheReq;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jjbicyclecache")
public interface StoreCacheService {

     @RequestMapping(value = "/geo/query/radiusGeo",method = RequestMethod.POST)
     AppletResult radiusGeo(@RequestBody GeoCacheReq geoCacheReq);

     @RequestMapping(value = "/geo/query/storecacheinfo",method = RequestMethod.POST)
     AppletResult queryStoreCacheInfo(@RequestBody String storeId);
}
