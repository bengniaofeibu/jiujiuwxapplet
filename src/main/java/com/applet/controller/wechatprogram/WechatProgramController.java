package com.applet.controller.wechatprogram;

import com.applet.annotation.SystemControllerLog;
import com.applet.controller.BaseController;
import com.applet.entity.wechatprogram.ActivitiesInfo;
import com.applet.entity.wechatprogram.BannerInfor;
import com.applet.entity.wechatprogram.UserInfo;
import com.applet.enums.ResultEnums;
import com.applet.service.WechatProgramService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BannerController
 *
 * @author hcx
 * @date 2018/03/08
 */
@RestController
@RequestMapping(value = "/wechat/program")
public class WechatProgramController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatProgramController.class);

    private final WechatProgramService wechatProgramService;
    @Autowired
    public WechatProgramController(WechatProgramService wechatProgramService) {
        this.wechatProgramService = wechatProgramService;
    }

    @SystemControllerLog(funcionExplain = "获取banner图片信息")
    @CrossOrigin
    @GetMapping(value = "/get/banners")
    public AppletResult getBanners(@RequestParam(value = "cityName") String cityName){
        try {
            Date now = new Date();
            List<BannerInfor> bannerInfoList = wechatProgramService.getBanners(cityName,now);
            if(bannerInfoList==null || bannerInfoList.size()==0){
            List<BannerInfor> bannerInfos = wechatProgramService.getBannerList(now);
                LOGGER.info("bannerInfos"+bannerInfos);
                return ResultUtil.success(bannerInfos);
            }
            LOGGER.info("bannerInfoList"+bannerInfoList);
            return ResultUtil.success(bannerInfoList);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }


    @SystemControllerLog(funcionExplain = "获取赳赳乐享列表信息")
    @CrossOrigin
    @GetMapping(value = "/get/jjlx")
    public AppletResult getJjlx(@RequestParam(value = "cityName") String cityName,@RequestParam(value = "os") Integer os){
        try {
            List<ActivitiesInfo> activitiesInfoList = wechatProgramService.getActivitiesInfos(cityName,os);
            LOGGER.info("activitiesInfoList"+activitiesInfoList);
            return ResultUtil.success(activitiesInfoList);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }



    @SystemControllerLog(funcionExplain = "获取钱包信息")
    @CrossOrigin
    @PostMapping(value = "/get/wallet")
    public AppletResult getWallet(@RequestBody Map map){
        String adminId = (String) map.get("adminId");
        Map<String, Object> content = new HashMap<>();
        try {
            UserInfo user = wechatProgramService.getUserByAdminId(adminId);
            Long couponNum = wechatProgramService.getCouponNum(adminId);
            content.put("luckyMoney", user.getLuckyMoney());
            content.put("phone", user.getPhone());
            content.put("deposit", user.getDeposit());
            content.put("couponNum", couponNum);
            return ResultUtil.success(content);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }
}
