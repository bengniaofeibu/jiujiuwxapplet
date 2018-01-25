package com.weichuxing.controller.UserController;

import com.weichuxing.annotation.SystemControllerLog;
import com.weichuxing.controller.BaseController;
import com.weichuxing.entity.WcxRequest.UserInfoRequest;
import com.weichuxing.entity.WcxResponse.UserInfoResponse;
import com.weichuxing.entity.WcxRequest.WcxUserRegisterInfoRequest;
import com.weichuxing.utils.WcxResult;
import com.weichuxing.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController extends BaseController {


    @SystemControllerLog(funcionExplain = "进入查询用户信息控制层")
    @GetMapping(value = "/vcx_query_user_info.fcgi")
    public WcxResult queryUserInfo(UserInfoRequest userInfo) {


        //解码和验证签名
        UserInfoRequest userInfoRequest = verificationSignAndDecode(userInfo, UserInfoRequest.class, null);

        //查询用户信息
        UserInfoResponse userInfoResponse = userInfoService.queryUserRegisterInfo(userInfoRequest);
        return ResultUtil.success(userInfoResponse);
    }

    @SystemControllerLog(funcionExplain = "进入通知新用户注册控制层")
    @PostMapping(value = "/nvcx_notify_user_regist.fcgi")
    public WcxResult notifyUserRegist(WcxUserRegisterInfoRequest wcxUserRegisterInfo) {

        //解码和验证签名
//        WcxUserRegisterInfoRequest wcxUserRegisterInfoRequest = verificationSignAndDecode(wcxUserRegisterInfo, WcxUserRegisterInfoRequest.class, null);

        //操作微出行注册用户信息
        userInfoService.notifyWcxUserRegisterInfo(wcxUserRegisterInfo);
        return ResultUtil.success();
    }
}
