package com.applet.controller.StoreController;

import com.applet.annotation.SystemControllerLog;
import com.applet.entity.store.AutoLoginReq;
import com.applet.entity.store.DuibaReq;
import com.applet.service.StoreService;
import com.applet.utils.AppletResult;
import com.applet.utils.ReturnDataUtil;
import com.applet.utils.common.JSONUtil;
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
        AppletResult result = storeService.duiba(req);
        ReturnDataUtil dataUtil = JSONUtil.parseObject(JSONUtil.toJSONString(result.getData()),ReturnDataUtil.class);
        String url=dataUtil.getReturnData().toString();
        //http://www.duiba.com.cn/autoLogin/autologin?uid=15554313215&credits=null&sign=4fbd01848f12b7e974893f11a288b87f&appKey=bHqyPxCrAChd1iDfUdLe1J4gdtJ&timestamp=1531469842586&
        url = url.substring(24);
        url = "https://home.m.duiba.com.cn/"+url;
        ReturnDataUtil returnDataUtil = new ReturnDataUtil();
        returnDataUtil.setReturnData(url);
        result.setData(returnDataUtil);
        return result;
    }
}
