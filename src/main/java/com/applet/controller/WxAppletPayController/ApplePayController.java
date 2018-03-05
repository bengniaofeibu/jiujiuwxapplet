package com.applet.controller.WxAppletPayController;

import com.applet.annotation.SystemControllerLog;
import com.applet.controller.BaseController;
import com.applet.entity.Cat;
import com.applet.entity.WxPay.WxAppletPayCallBack;
import com.applet.entity.WxPay.WxAppletPayRequest;
import com.applet.enums.ResultEnums;
import com.applet.enums.WxCallBackResultEnums;
import com.applet.mapper.AmountRecordMapper;
import com.applet.mapper.UserInfoMapper;
import com.applet.model.AmountRecord;
import com.applet.service.ConsumerService;
import com.applet.service.WxAppleetPayService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.WxAppletServiceUtil;
import com.applet.utils.common.JSONUtil;
import com.applet.utils.common.StreamUtil;
import com.applet.utils.common.XmlOrMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/pay")
public class ApplePayController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplePayController.class);


    @Autowired
    private ConsumerService consumerService;



    @SystemControllerLog(funcionExplain = "进入微信支付控制层")
    @GetMapping(value = "/wx_xcx_appletpay")
    public AppletResult appletPlay(WxAppletPayRequest wxAppletPayRequest, HttpServletRequest request, @RequestHeader("session") String session) {
        try {

            String remoteAddr = request.getRemoteAddr();

            AppletResult appletResult=consumerService.appletPlay(wxAppletPayRequest,remoteAddr,session);
            return appletResult;
        } catch (Exception e) {
            LOGGER.error("ERROR {}", e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
