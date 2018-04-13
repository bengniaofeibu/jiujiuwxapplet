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
    @PostMapping(value = "/get/banners")
    public AppletResult getBanners(@RequestBody Map map){
        String cityName = (String)map.get("cityName");
        Integer type = Integer.parseInt((String)map.get("type"));
        try {


            Date now = new Date();
            List<BannerInfor> bannerInfoList = wechatProgramService.getBanners(cityName,now,type);
            if(bannerInfoList==null || bannerInfoList.size()==0){
            List<BannerInfor> bannerInfos = wechatProgramService.getBannerList(now,type);
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

    @SystemControllerLog(funcionExplain = "获取红包提示")
    @CrossOrigin
    @PostMapping(value = "/get/luckMoneyPrompt")
    public AppletResult getLuckMoneyPrompt(@RequestParam(value = "cityName") String cityName,@RequestParam(value = "userId") String userId){
        return wechatProgramService.getLuckMoneyPrompt(userId);
    }

    @SystemControllerLog(funcionExplain = "获取赳赳乐享列表信息")
    @CrossOrigin
    @GetMapping(value = "/get/jjlx")
    public AppletResult getJjlx(@RequestParam(value = "cityName") String cityName,@RequestParam(value = "os") Integer os){
        try {
            List<ActivitiesInfo> activitiesInfoList = wechatProgramService.getActivitiesInfos(cityName,os);
            for(ActivitiesInfo activitiesInfo:activitiesInfoList){
                activitiesInfo.setBeginTime(activitiesInfo.getBeginTime().substring(5,10).replaceFirst("-","月")+"日");
                activitiesInfo.setEndTime(activitiesInfo.getEndTime().substring(5,10).replaceFirst("-","月")+"日");
            }
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
            content.put("luckyMoney", user.getLuckyMoney().toString());
            content.put("phone", user.getPhone());
            content.put("deposit", user.getDeposit().toString());
            content.put("couponNum", couponNum);
            return ResultUtil.success(content);
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }



    /*@SystemControllerLog(funcionExplain = "获取电子围栏信息")
    @CrossOrigin
    @PostMapping(value = "/get/electricFance")
    public AppletResult getElectricFance(@RequestBody Map map){
        String areaName = ((String)map.get("cityName")).toLowerCase();
        Map<String, Object> content = new HashMap<>();
        String resultStr;
        try {
            resultStr = redisUtil.getValuesStr("getElectricFance_" + areaName);
            if (StringUtils.isEmpty(resultStr)) {
                List<ElectricFanceInfo> points = wechatProgramService.findFanceByAreaName(areaName);
                if (points != null && points.size() != 0) {
                    for (ElectricFanceInfo point : points) {
                        if (content.containsKey("fanceType" + point.getType().toString())) {
                            //根据type取出助力车和自行车的电子围栏
                            Map<String, StringBuilder> valueMap = (Map) content.get("fanceType" + point.getType().toString());
                            //再根据fanceNO取出某一个电子围栏
                            if (valueMap.containsKey(point.getFanceNo().toString())) {
                                valueMap.put(point.getFanceNo().toString(), valueMap.get(point.getFanceNo().toString()).append(";").append(point.getLongitude()).append(",").append(point.getLatitude()));
                            } else {
                                valueMap.put(point.getFanceNo().toString(), new StringBuilder("").append(point.getLongitude()).append(",").append(point.getLatitude()));
                            }

                        }else {


                        }
                    }
                    return ResultUtil.success();
                }else {
                    return ResultUtil.success("当前地区无围栏");
                }
            }else {
                return ResultUtil.error(ResultEnums.ELECTRICFANCE_VALIDATION_FAIL);
            }
        }catch (Exception e){
            LOGGER.info(e.getMessage());
            return ResultUtil.error(ResultEnums.SERVER_ERROR);
        }
    }*/


}
