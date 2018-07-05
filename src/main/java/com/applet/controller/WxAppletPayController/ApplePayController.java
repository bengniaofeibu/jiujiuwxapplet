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
import com.applet.model.UserInfo;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/pay")
public class ApplePayController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplePayController.class);


    @Value("${pay.amount}")
    private String amount;


    @Autowired
    private ConsumerService consumerService;



    @SystemControllerLog(funcionExplain = "进入微信支付控制层")
    @GetMapping(value = "/wx_xcx_appletpay")
    public AppletResult appletPlay(WxAppletPayRequest wxAppletPayRequest, HttpServletRequest request, @RequestHeader("session") String session) {
        try {

            UserInfo userInfo = getUserInfoByUserId(wxAppletPayRequest.getAdminId());
            LOGGER.debug("userInfo {}",JSONUtil.toJSONString(userInfo));

            if (userInfo!= null && (userInfo.getAccountStatus() == 1 || userInfo.getAccountStatus() == 3 )){
                  return ResultUtil.error(ResultEnums.USER_ALREADY_RECHARGE);
            }

            String remoteAddr = request.getRemoteAddr();
            wxAppletPayRequest.setAmount(amount);
            AppletResult appletResult=consumerService.appletPlay(wxAppletPayRequest,remoteAddr,session);
            return appletResult;
        } catch (Exception e) {
            LOGGER.error("ERROR {}", e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
