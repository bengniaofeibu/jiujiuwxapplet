package com.applet.service;


import com.applet.entity.AcquireJiuMiReq;
import com.applet.entity.MyJiuMiReq;
import com.applet.entity.UserInfo.WxUserInfoReq;
import com.applet.entity.WxPay.WxAppletPayRequest;
import com.applet.utils.AppletResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jjbicycleuser")
public interface UserJiuMiService {

    /**
     * 我的赳米信息
     * @param myJiuMiReq
     * @return
     */
    @RequestMapping(value = "/jm/get/myJiuMi",method = RequestMethod.POST )
    AppletResult getMyJiuMi(@RequestBody MyJiuMiReq myJiuMiReq);


    /**
     * 查看赳米任务
     * @param myJiuMiReq
     * @return
     */
    @RequestMapping(value = "/jm/get/myJiuMiMission",method = RequestMethod.POST )
    AppletResult getMyJiuMission(@RequestBody MyJiuMiReq myJiuMiReq);


    /**
     * 获取首页罚款弹框
     * @param wxUserinfoReq
     * @return
     */
    @RequestMapping(value = "/user/query/finebounced",method = RequestMethod.POST )
    AppletResult getFineBounced(@RequestBody WxUserInfoReq wxUserinfoReq);


    /**
     * 获取赳米场景
     * @param acquireJiuMiReq
     * @return
     */
    @RequestMapping(value = "/jm/do/acquireJiuMi",method = RequestMethod.POST )
    AppletResult doAcquireJiuMi(@RequestBody AcquireJiuMiReq acquireJiuMiReq);

}
