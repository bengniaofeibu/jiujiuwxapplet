package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.home.HomeRes;
import com.applet.mapper.ActivitiesInfoMapper;
import com.applet.mapper.AppDisplayMapper;
import com.applet.mapper.UserInfoMapper;
import com.applet.model.ActivitiesInfo;
import com.applet.model.AppDisplay;
import com.applet.model.UserInfo;
import com.applet.service.HomeService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import jiujiu.Util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private AppDisplayMapper appDisplayMapper;

    @Autowired
    private ActivitiesInfoMapper activitiesInfoMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;
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
            }
            return ResultUtil.success(list.get(0));
        }else {
            return ResultUtil.success(list);
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
}
