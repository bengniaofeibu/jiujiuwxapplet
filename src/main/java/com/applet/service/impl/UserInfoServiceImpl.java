package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.AcquireJiuMiReq;
import com.applet.entity.UserInfo.UserInfoResponse;
import com.applet.entity.UserInfo.UserJiuMiRankListRes;
import com.applet.entity.Wx.WxPublicText;
import com.applet.entity.Wx.WxPublicUserInfo;
import com.applet.enums.ResultEnums;
import com.applet.mapper.*;
import com.applet.model.*;
import com.applet.service.UserInfoService;
import com.applet.service.UserJiuMiService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private WxUserInfoMapper wxUserInfoMapper;

    @Autowired
    private UserReportMapper userReportMapper;

    @Autowired
    private TransRecordTempMapper transRecordTempMapper;

    @Autowired
    private OperateConfigMapper operateConfigMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @Autowired
    private LoginDailyLogMapper loginDailyLogMapper;

    @Autowired
    private JiumiLogMapper jiumiLogMapper;

    @Autowired
    private JiumiMissionMapper jiumiMissionMapper;

    @Autowired
    private FineDetailMapper fineDetailMapper;

    @Autowired
    private UserJiuMiService userJiuMiService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private EsUtil esUtil;

    @Autowired
    private WxPublicInfoMapper wxPublicInfoMapper;

    @Autowired
    private WxPublicUtil wxPublicUtil;

    @Autowired
    private BicycleWxUserInfoMapper bicycleWxUserInfoMapper;

    private static final int FREE_DEPOSIT_STATUS_NO = 0;

    private static final int FREE_DEPOSIT_STATUS_YES = 1;

    public static final String USER_LOGIN_KEY = "user:login:flag";

    public static final String USER_JIUMI_TOTAL = "user:jiumi:total";

    private static final String USER_PICURL_PREFIX = "https://jjdc-client.oss-cn-shanghai.aliyuncs.com/";

    private static final String CREDIT_NOT_DEFICIENCY = "您的信用分不足，无法骑行";

    private static final String JIUMI_NOT_DEFICIENCY = "您的赳米数量不足，无法骑行";

    private static final String WX_PUBLIC_DES = "完成公众号任务";

    private static final String SUBSCRIBE = "subscribe";

    private static final String UNSUBSCRIBE = "unsubscribe";

    private static final Integer EXPIRE_TIME = 43200;

    @Value("${spring.data.elasticsearch.index}")
    private String esIndex;

    @Value("${spring.data.elasticsearch.type}")
    private String esType;


    @Override
    @SystemServerLog(funcionExplain = "添加新用户注册")
    @Transactional(rollbackFor = Exception.class)
    public UserInfoResponse addRegisterUser(UserInfo userInfo, WxUserInfo wxUserInfo) {
        if (userInfo.getUserSource() != 0) {
            userInfoMapper.insertSelective(userInfo);
            LOGGER.debug("记录单车用户信息 {}", JSONUtil.toJSONString(userInfo));
        }

        wxUserInfo.setUserId(userInfo.getId());
        wxUserInfoMapper.insertSelective(wxUserInfo);
        LOGGER.debug("记录微信用户信息 {}", JSONUtil.toJSONString(wxUserInfo));

        int userReportCount = userReportMapper.selectUserIdByUserId(wxUserInfo.getUserId());
        if (userReportCount == 0) {
            UserReport userReport = new UserReport(UuidUtil.getUuid(), 0.0, 0.0, 0.0, userInfo.getId());
            userReportMapper.insertUserReportInfo(userReport);
            LOGGER.debug("记录用户report信息 {}", JSONUtil.toJSONString(userReport));
        }

        UserInfoResponse info = new UserInfoResponse();
        info.setAdminId(userInfo.getId());
        info.setStatus(userInfo.getAccountStatus());

        if (wxUserInfo.getJiuMiShowFlag() == 0) {

            if (userInfo.getUserSource() != 0) {

                //添加新用户注册赳米记录
                jiumiLogMapper.insertJiuMiLog(new JiumiLog(userInfo.getId(), 13, 50L, 0, "新用户注册"));

                //添加新用户注册额外赠送赳米记录
                jiumiLogMapper.insertJiuMiLog(new JiumiLog(userInfo.getId(), 13, 30L, 0, "额外赠送"));

            } else {

                //新用户登录
                userJiuMiService.doRegisterByFriend(userInfo.getId());

            }

        }

        return info;
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    @SystemServerLog(funcionExplain = "获取用户信息")
    public UserInfoResponse getUserInfo(String openId, String id, String cityName) {
        UserInfo info = userInfoMapper.selectUserInfoById(id);
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setStatus(info.getAccountStatus());
        Integer borrowBicycle = info.getmBorrowBicycle();
        userInfoResponse.setBorrowBicycle(borrowBicycle);

        SysArea sysArea = sysAreaMapper.selecIdtByCountName(cityName);
        if (sysArea != null) {
            OperateConfig operateConfig = operateConfigMapper.selectLimitHourByCity(sysArea.getId());

            if (operateConfig != null) {
                setFreeDepositStatus(operateConfig.getLimitHour(), info, userInfoResponse);
            } else {
                OperateConfig countryConfig = operateConfigMapper.selectLimitHourByCountry();
                if (countryConfig != null) {
                    setFreeDepositStatus(countryConfig.getLimitHour(), info, userInfoResponse);
                } else {
                    userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
                }
            }
        } else {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
        }

        if (borrowBicycle.intValue() == 1) {
            long borrowBicycleDate = (new Date().getTime() - info.getmBorrowBicycleDate().getTime()) / (1000);
            userInfoResponse.setRidingTime(borrowBicycleDate);
            int borrowBicycleNum = transRecordTempMapper.selectByUserIdAndTransFlag(id).getBorrowBicycleNum();
            userInfoResponse.setUserBicycleNo(borrowBicycleNum);
        }

        //查询赳米是否已经关闭
        int count = jiumiMissionMapper.selectCountByOnOff();
        userInfoResponse.setJiuMiShowFlag(count > 0 ? 0 : 1);

        //判断信用分是否大于70分 小于70不能骑行
        if (info.getCreditScore() >= 70) {
            if (count == 0) {

                if (info.getIntegral() >= -100) {
                    userInfoResponse.setIsCanCyclingFlag(1);
                } else {
                    userInfoResponse.setNotCanCylingMsg(JIUMI_NOT_DEFICIENCY);
                    userInfoResponse.setIsCanCyclingFlag(0);
                }

            } else {
                userInfoResponse.setIsCanCyclingFlag(1);
            }
        } else {
            userInfoResponse.setNotCanCylingMsg(CREDIT_NOT_DEFICIENCY);
            userInfoResponse.setIsCanCyclingFlag(0);
        }

        int fineCont = fineDetailMapper.selectCountByUserIdAndStatus(id);
        userInfoResponse.setIsFine(fineCont == 0 ? 0 : 1);

        userInfoResponse.setUserWxOpendId(openId);

        String userLoginOpenId = redisUtil.getValuesStr(USER_LOGIN_KEY+info.getPhone());

        userInfoResponse.setUserLoginOpenId(StringUtils.isBlank(userLoginOpenId)?openId:userLoginOpenId);

        return userInfoResponse;
    }


    /**
     * 记录用户打开小程序
     *
     * @param userInfo
     */
    @SystemServerLog(funcionExplain = "记录用户打开小程序")
    @Override
    public AppletResult recordUserOpenXcx(UserInfo userInfo) {

        //查询用户当天有没有打开小程序记录
        long count = loginDailyLogMapper.selectUserCurdateCountById(userInfo.getId());

        if (count == 0) {
            //记录用户当天打开小程序记录
            LoginDailyLog loginDailyLog = new LoginDailyLog();
            loginDailyLog.setId(UuidUtil.getUuid());
            loginDailyLog.setUserId(userInfo.getId());
            loginDailyLog.setAppVersion("");
            loginDailyLog.setPhoneSystemVersion("");
            loginDailyLogMapper.insertUserLoginRecord(loginDailyLog);
        }


        //存储到es
        esUtil.save(esIndex, esType, userInfo);

        return ResultUtil.success();
    }

    /**
     * 获取用户赳米周榜
     *
     * @param userId
     * @return
     */
    @SystemServerLog(funcionExplain = "获取用户赳米周榜")
    @Override
    public AppletResult getUserJiuMiWeekRankingList(String userId, Integer rankingType) {

        List<JiumiLog> jiumiLogs = getJiumiLogs(rankingType);

        if (jiumiLogs == null || jiumiLogs.size() == 0) {
            return ResultUtil.error(ResultEnums.NOT_FOUND_RANKING_LIST_FAIL);
        }

        JiumiLog jiumiInfo = getJiumiLog(rankingType, userId);
        LOGGER.debug("jiumiLogs {}  jiumiLog{} ", JSONUtil.toJSONString(jiumiLogs), JSONUtil.toJSONString(jiumiInfo));

        //设置用户头像
        jiumiLogs = setUserHead(jiumiLogs);

        List<String> userIds = jiumiLogs.stream().map(jiumiLog -> jiumiLog.getUserId()).collect(Collectors.toList());

        UserJiuMiRankListRes userJiuMiRankListRes = new UserJiuMiRankListRes();
        userJiuMiRankListRes.setJiumiLogs(jiumiLogs);

        //如果在排名内
        if (userIds.contains(userId)) {

            int index = userIds.indexOf(userId);

            if (index == 0){

                userJiuMiRankListRes.setJiuSumDiff(0);

            }else {

                //获取上一名的赳米数
                Integer jiuSum = jiumiLogs.get(index - 1).getJiuSum();

                if (rankingType == 1) {

                    Integer meJiuSum = jiumiLogs.get(index).getJiuSum();
                    userJiuMiRankListRes.setJiuSumDiff(jiuSum - meJiuSum);

                    if (jiumiInfo != null) {
                        jiumiInfo.setJiuSum(meJiuSum);
                    }

                } else {

                    userJiuMiRankListRes.setJiuSumDiff(jiuSum - jiumiInfo.getJiuSum());
                }
            }

            userJiuMiRankListRes.setRankListFlag(1);
            userJiuMiRankListRes.setMyJiuMiRanking(index + 1);
        }

        userJiuMiRankListRes.setJiumiLog(jiumiInfo == null ? new JiumiLog() : jiumiInfo);
        return ResultUtil.success(userJiuMiRankListRes);
    }

    /**
     * 记录用户unionId
     *
     * @param wxUserInfo
     * @return
     */
    @SystemServerLog(funcionExplain = "记录用户unionId")
    @Override
    public AppletResult recordUserUnionId(WxUserInfo wxUserInfo) {



        //更新用户unionId
        wxUserInfoMapper.updateUserStatusById(wxUserInfo);

        WxUserInfo userInfo = new WxUserInfo(WX_PUBLIC_DES, WxPublicText.JIUMI_NUM);

        if (wxPublicInfoMapper.selectCountByUnionId(wxUserInfo.getUnionId()) > 0) {

            //送赳米
            userJiuMiService.doAcquireJiuMi(new AcquireJiuMiReq(3,1,wxUserInfo.getUserId()));

            userInfo.setIsCompleteFocus(1);
            return ResultUtil.success(userInfo);
        }


        return ResultUtil.success(userInfo);
    }


    /**
     * 获取用户关注公众号或取消公众号信息
     *
     * @param request
     * @return
     */
    @SystemServerLog(funcionExplain = "获取用户关注公众号或取消公众号信息")
    @Override
    public String getWeiXinPublicFollowOrCancel(HttpServletRequest request) throws Exception {


        Map<String, String> map = XmlOrMapUtils.xmlToMap(StreamUtil.inputStream2String(request.getInputStream(), "UTF-8"));
        LOGGER.debug("微信公众号返回数据 {}", map);

        if (map != null && map.size() > 0) {

            String event = map.get("Event");

            String openId = map.get("FromUserName");

            WxPublicInfo wxPublicInfo = new WxPublicInfo();

            switch (event) {

                case SUBSCRIBE:
                    WxPublicUserInfo publicUserInfo = wxPublicUtil.getWeiXinPublicUserInfo(openId);

                    if (publicUserInfo.getSubscribe() == 1) {

                        wxPublicInfo.setUnionId(publicUserInfo.getUnionid());
                        wxPublicInfo.setOpenId(publicUserInfo.getOpenid());
                        wxPublicInfo.setCity(publicUserInfo.getCity());
                        wxPublicInfo.setNickName(publicUserInfo.getNickname());
                        wxPublicInfo.setCountry(publicUserInfo.getCountry());
                        wxPublicInfo.setHeadImageUrl(publicUserInfo.getHeadimgurl());
                        wxPublicInfo.setLanguage(publicUserInfo.getLanguage());
                        wxPublicInfo.setProvince(publicUserInfo.getProvince());
                        wxPublicInfo.setSubscribe(publicUserInfo.getSubscribe().shortValue());
                        wxPublicInfo.setSex(publicUserInfo.getSex().shortValue());
                        wxPublicInfo.setSubscribeTime(new Date(publicUserInfo.getSubscribe_time()));

                    }
                    break;

                case UNSUBSCRIBE:
                    wxPublicInfo.setOpenId(openId);
                    wxPublicInfo.setSubscribe((short) 0);
                    break;

                default:
                    return "";
            }


            int count = wxPublicInfoMapper.selectCountByOpenId(openId);

            if (count > 0) {

                if (SUBSCRIBE.equals(event)) {
                    wxPublicInfo.setSubscribe((short) 1);
                }
                //更新关注微信公众号用户信息
                wxPublicInfoMapper.updateWxPublicUserInfoByOpenid(wxPublicInfo);

            } else {

               String wxUserId = wxUserInfoMapper.selectUserIdByUnionId(wxPublicInfo.getUnionId());

               String bicycleWxUserId = bicycleWxUserInfoMapper.selectUserIdByUnionId(wxPublicInfo.getUnionId());

                int jiuMiFlag = jiumiMissionMapper.selectCountByOnOff();

                //判断赳米是否开启 0 说明没有查询到关闭赳米的标识
                if (jiuMiFlag == 0){

                    if (!StringUtils.isBlank(wxUserId) || !StringUtils.isBlank(bicycleWxUserId)) {

                        //发送送赳米消息
                        wxPublicUtil.sendWxTest(wxPublicInfo);

                        //送赳米
                        userJiuMiService.doAcquireJiuMi(new AcquireJiuMiReq(3, 1, StringUtils.isBlank(wxUserId) ? bicycleWxUserId : wxUserId));
                    }

                }

                //记录关注微信公众号用户信息
                wxPublicInfoMapper.insertWxPublicUserInfo(wxPublicInfo);
            }
        }
        return "";
    }

    //设置用户免押状态
    private void setFreeDepositStatus(int limitHour, UserInfo userInfo, UserInfoResponse userInfoResponse) {
        boolean eAppointTime = DateUtil.isEAppointTime(userInfo.getAddTime(), new Date(), limitHour, DateUtil.TimeType.HOURS);

        //判断是否超过指定免交押金时间
        if (eAppointTime) {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_NO);
        } else {
            userInfoResponse.setFreeDepositStatus(FREE_DEPOSIT_STATUS_YES);
        }
    }

    public static List<JiumiLog> setUserHead(List<JiumiLog> jiumiLogs) {

        for (JiumiLog jiumiLog : jiumiLogs) {
            String picurl = jiumiLog.getPicurl();
            jiumiLog.setPicurl(setUserPicurl(picurl));
        }
        return jiumiLogs;
    }

    private static String setUserPicurl(String picurl) {
        String userPicurl;
        if (!picurl.startsWith("http") && !picurl.startsWith("https") && !StringUtils.isBlank(picurl)) {

            userPicurl = new StringBuilder(USER_PICURL_PREFIX).append(picurl).toString();

        } else if (StringUtils.isBlank(picurl)) {

            userPicurl = JiumiLog.DEFAULT_USER_PICURL;

        } else {

            userPicurl = picurl;

        }
        return userPicurl;
    }

    public List<JiumiLog> getJiumiLogs(int type) {
        List<JiumiLog> jiumiLogs = null;
        switch (type) {
            case 0:
                jiumiLogs = jiumiLogMapper.selectWeekRankingList(DateUtil.getMondayDate());
                break;
            case 1:
                jiumiLogs = (List<JiumiLog>) redisUtil.getValueObj(USER_JIUMI_TOTAL);

                JiumiLog jiumiLog;
                if (jiumiLogs != null && jiumiLogs.size() > 0) {
                    List<JiumiLog> jiumiLogList = new LinkedList<>();
                    for (int i = 0; i < jiumiLogs.size(); i++) {
                        jiumiLog = JSONUtil.parseObject(JSONUtil.toJSONString(jiumiLogs.get(i)), JiumiLog.class);
                        jiumiLogList.add(jiumiLog);
                    }
                    jiumiLogs = jiumiLogList;
                } else {
                    jiumiLogs = jiumiLogMapper.selectTotalJiuMiNum();
                    redisUtil.setObjAndExpire(UserInfoServiceImpl.USER_JIUMI_TOTAL, jiumiLogs, EXPIRE_TIME);
                }

                break;
        }

        //过滤出赳米总数小于0的
        jiumiLogs = jiumiLogs.stream().filter(x -> x.getJiuSum() >= 0).collect(Collectors.toList());
        return jiumiLogs;
    }

    private JiumiLog getJiumiLog(int type, String userId) {
        JiumiLog jiumiLog = null;
        switch (type) {
            case 0:
                jiumiLog = jiumiLogMapper.selectWeekJiuMiNumByUserId(userId, DateUtil.getMondayDate());
                break;
            case 1:
                jiumiLog = jiumiLogMapper.selectTotalJiuMiNumByUserId(userId);
                break;
        }

        if (jiumiLog != null) {
            jiumiLog.setPicurl(setUserPicurl(jiumiLog.getPicurl()));
        } else {
            UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
            return new JiumiLog(setUserPicurl(userInfo.getPicurl()));
        }

        return jiumiLog;
    }
}
