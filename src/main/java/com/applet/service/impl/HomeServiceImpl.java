package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.home.HomeRes;
import com.applet.enums.ResultEnums;
import com.applet.mapper.*;
import com.applet.model.ActivitiesInfo;
import com.applet.model.AppDisplay;
import com.applet.model.UserInfo;
import com.applet.model.WxUserInfo;
import com.applet.service.HomeService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import jiujiu.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private AppDisplayMapper appDisplayMapper;

    @Autowired
    private ActivitiesInfoMapper activitiesInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BicycleWxUserInfoMapper bicycleWxUserInfoMapper;

    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;
    /**
     * 获取二级弹窗
     *
     * @param cityName
     * @return
     */
    @SystemServerLog(funcionExplain = "获取二级弹窗")
    @Override
    public AppletResult getPopup(String cityName) {

        List<AppDisplay> displays = appDisplayMapper.selectPopup(cityName);

        List<HomeRes> list = new ArrayList<>();

        if (displays!=null && displays.size()>0){
            for(AppDisplay appDisplay: displays){
                HomeRes res = new HomeRes();
                if (appDisplay.getId()!=null){
                    res.setId(appDisplay.getId());
                }
                if (appDisplay.getDisplayWords()!=null){
                    res.setDisplayWords(appDisplay.getDisplayWords());
                }

                if (appDisplay.getActionType()!=null){
                    res.setActionType(appDisplay.getActionType().intValue());
                }

                if (appDisplay.getActivityId()!=null&&appDisplay.getActivityId()!=""){
                    ActivitiesInfo activitiesInfo = activitiesInfoMapper.selectByPrimaryKey(appDisplay.getActivityId());
                    if (activitiesInfo!=null){
                        if (StringUtil.isEmpty(activitiesInfo.getImgPath2())){
                            res.setPicUrl("");
                        }else {
                            res.setPicUrl(activitiesInfo.getImgPath2().split(",")[0]);
                        }
                        if (StringUtil.isEmpty(activitiesInfo.getActivityPath())){
                            res.setActionUrl("");
                        }else {
                            res.setActionUrl(activitiesInfo.getActivityPath());
                        }
                    }
                    list.add(res);
                }else {
                    res.setPicUrl(appDisplay.getDisplayPic());
                    res.setActionUrl(null);
                    list.add(res);
                }

            }
            Random random = new Random();
            int nextInt = random.nextInt(list.size());
            return ResultUtil.success(list.get(nextInt));
        }else {
            return ResultUtil.error(ResultEnums.NO_POP);
        }

    }

    /**
     * 获取二级弹窗
     *
     * @param phone
     * @return
     */
    @SystemServerLog(funcionExplain = "校验手机号是否已经绑定")
    @Override
    public UserInfo selectByUserPhone(String phone) {
        return userInfoMapper.selectByUserPhone(phone);
    }

    /**
     * 修改手机号
     *
     * @param userInfo
     * @return
     */
    @SystemServerLog(funcionExplain = "修改手机号")
    @Override
    public int updatePhone(UserInfo userInfo) {
        return userInfoMapper.updatePhone(userInfo);
    }

    @Override
    public UserInfo selectOldPhone(UserInfo oldUser) {
        return userInfoMapper.selectOldPhone(oldUser);
    }

    /**
     * 修改微信手机号
     *
     * @param wxUserInfo
     * @return
     */
    @SystemServerLog(funcionExplain = "修改微信手机号")
    @Override
    public int updateWxUser(WxUserInfo wxUserInfo) {
        return wxUserInfoMapper.updateWxUser(wxUserInfo);
    }
}
