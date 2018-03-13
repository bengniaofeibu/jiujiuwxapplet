package com.applet.controller.LockController;

import com.applet.annotation.SystemControllerLog;
import com.applet.controller.BaseController;
import com.applet.entity.Cat;
import com.applet.entity.LockRequest.EndOrderRequest;
import com.applet.entity.LockRequest.QueryRidingStatusRequest;
import com.applet.entity.LockRequest.ScaveningUnlockRequest;
import com.applet.enums.ResultEnums;
import com.applet.model.SysDict;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;

import java.util.List;

@RestController
@RequestMapping("/ride")
public class RidingController extends BaseController{

    private static final Logger LOGGER= LoggerFactory.getLogger(RidingController.class);

    @SystemControllerLog(funcionExplain = "查询用户状态")
    @GetMapping(value = "/wx_xcx_query_riding_status")
    public AppletResult queryRidingStatus(QueryRidingStatusRequest queryRidingStatusRequest){
        AppletResult appletResult = ridingService.queryRidingStatus(queryRidingStatusRequest);
        return appletResult;
    }

    @SystemControllerLog(funcionExplain = "故障报修")
    @GetMapping(value = "/wx_xcx_end_order")
    public AppletResult endOrder(EndOrderRequest endOrderRequest, @RequestHeader("session") String session){

        Cat authInfo = getAuthInfo(session);
        LOGGER.debug(" authInfo {}", JSONUtil.toJSONString(authInfo));

        endOrderRequest.setOpenId(authInfo.getOpenId());
        AppletResult appletResult = ridingService.endOrder(endOrderRequest);
        return appletResult;
    }


    @SystemControllerLog(funcionExplain = "进入获取故障保修列表控制层")
    @GetMapping(value ="/wx_xcx_get_fw_List")
    public AppletResult getFailureWarrantyList(String type){
        try {
            List<SysDict> failureWarrantyList = ridingService.getFailureWarrantyList(type);
            return ResultUtil.success(failureWarrantyList);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
           return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
