package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.LockRequest.ScaveningUnlockRequest;
import com.applet.entity.LockResponse.ScaveningUnlockResponse;
import com.applet.enums.ResultEnums;
import com.applet.mapper.*;
import com.applet.model.JiumiLog;
import com.applet.model.TransRecordSupply;
import com.applet.model.TransRecordTemp;
import com.applet.model.UserInfo;
import com.applet.service.ScavengingUnlockService;
import com.applet.service.UserJiuMiService;
import com.applet.utils.AppletResult;
import com.applet.utils.HttpClient.HttpLockApiUtils;
import com.applet.utils.ResultUtil;
import com.applet.utils.common.CommonUtils;
import com.applet.utils.common.UuidUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Service
public class ScavengingUnlockServiceImpl implements ScavengingUnlockService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private TransRecordTempMapper transRecordTempMapper;

    @Autowired
    private TransRecordSupplyMapper transRecordSupplyMapper;

    @Autowired
    private JiumiLogMapper jiumiLogMapper;

    @Autowired
    private JiumiMissionMapper jiumiMissionMapper;

    private static final String DESC = "小程序骑行消费";

    private static final Logger LOGGER = LoggerFactory.getLogger(ScavengingUnlockServiceImpl.class);

    @Override
    @SystemServerLog(funcionExplain = "扫码开锁")
    @Transactional(rollbackFor = Exception.class)
    public AppletResult scaveningUnlock(ScaveningUnlockRequest scaveningUnlockRequest) {

        String bicycleNo = CommonUtils.DecodeBarcode(scaveningUnlockRequest.getBarcode());
        if (!bicycleNo.equals("0")) {
            UserInfo userInfo = userInfoMapper.selectUserInfoById(scaveningUnlockRequest.getId());
            if (userInfo != null) {

                int jiuMiCount = jiumiMissionMapper.selectCountByOnOff();

                if (jiuMiCount == 0) {

                    if (userInfo.getIntegral() < -100) {

                        return ResultUtil.error(ResultEnums.JIU_MI_INSUFFICIENT);

                    }
                }

                if (scaveningUnlockRequest.getFreeDepositStatus().equals(0) && (userInfo.getAccountStatus() == 0 || userInfo.getAccountStatus() == 2)) {

                    return ResultUtil.error(ResultEnums.USER_NON_RECHARGE);

                } else {
                    if (userInfo.getmBorrowBicycle() == 1) {
                        return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_ORDERNOTFINISH_ERROR);
                    } else {
                        JSONObject jsonObject = HttpLockApiUtils.GetBikeInfoByBicycleNo(bicycleNo);
                        if (jsonObject != null) {
                            String bikeInfo = jsonObject.get("bikeInfo").toString();
                            LOGGER.debug("bikeInfo {}", bikeInfo);
                            String lockGPSRealData = jsonObject.get("lockGPSRealData").toString();
                            JSONObject jsonBikeInfo = new JSONObject(bikeInfo);
                            JSONObject jsonLockGPRSRealData = new JSONObject(lockGPSRealData);
                            if (Integer.parseInt(jsonLockGPRSRealData.get("batteryLevel").toString()) >= 20) {

                                 String transRecordOrderId = UuidUtil.getUuid();

                                if (jsonBikeInfo.get("lockSeries") != null) {
                                    if (Integer.parseInt(jsonBikeInfo.get("lockSeries").toString()) == 3) {
                                        //短信开锁
                                        int resSmsOpenlock = openLockBySms(scaveningUnlockRequest, jsonBikeInfo, userInfo, transRecordOrderId);
                                        if (resSmsOpenlock == 1) {
                                            updateUserJiuMi(scaveningUnlockRequest.getJiuMiShowFlag(), userInfo.getId(),transRecordOrderId);
                                            return ResultUtil.success();
                                        } else {
                                            return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_FAILSMSOPENLOCK);
                                        }
                                    } else {
                                        //GPRS开锁
                                        int resGprsOpenlock = openLockByGprs(scaveningUnlockRequest, jsonBikeInfo, userInfo, transRecordOrderId);
                                        if (resGprsOpenlock == 1) {
                                            updateUserJiuMi(scaveningUnlockRequest.getJiuMiShowFlag(), userInfo.getId(),transRecordOrderId);
                                            return ResultUtil.success();
                                        } else {
                                            return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_FAILGPRSOPENLOCK);
                                        }
                                    }
                                } else {
                                    //GPRS开锁
                                    int resGprsOpenlock = openLockByGprs(scaveningUnlockRequest, jsonBikeInfo, userInfo, transRecordOrderId);
                                    if (resGprsOpenlock == 1) {
                                        updateUserJiuMi(scaveningUnlockRequest.getJiuMiShowFlag(), userInfo.getId(),transRecordOrderId);
                                        return ResultUtil.success();
                                    } else {
                                        return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_FAILGPRSOPENLOCK);
                                    }
                                }
                            } else {
                                return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_LOWBATTERYLEVEL);
                            }
                        } else {
                            return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_BICYCLENONOTFINISH);
                        }
                    }
                }
            } else {
                return ResultUtil.error(ResultEnums.INVALID_USER);
            }
        } else {
            return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_ERRORBARCODE);
        }

    }

    //判断是否扣除赳米
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserJiuMi(Integer jiuMiShowFlag, String userId,String transRecordOrderId) {
        if (jiuMiShowFlag != null && jiuMiShowFlag == 1) {

            userInfoMapper.updateJiuMiByUserId(new UserInfo(userId, 10));

            //记录骑行赳米记录
            jiumiLogMapper.insertJiuMiLog(new JiumiLog(userId, 19, -10L, transRecordOrderId, 0, DESC));
        }
    }

    private int openLockBySms(ScaveningUnlockRequest scaveningUnlockRequest, JSONObject jsonBikeInfo, UserInfo userInfo, String transRecordOrderId) {
        int res = HttpLockApiUtils.OpenLockBySms(jsonBikeInfo.get("gprsNo").toString());
        if (res == 1) {
            //生成订单
            long orderNum = System.currentTimeMillis() * 100 + CommonUtils.getRandom(100);
            TransRecordTemp transRecordTemp = new TransRecordTemp();
            transRecordTemp.setBorrowBicycleNum(Integer.parseInt(jsonBikeInfo.get("bicycleNo").toString()));
            transRecordTemp.setId(transRecordOrderId);
            transRecordTemp.setBorrowBatteryNum(0);
            transRecordTemp.setBorrowDateTime(new Date());
            transRecordTemp.setOrderIntegral(0);
            transRecordTemp.setOrderNum(orderNum);
            transRecordTemp.setRecessionBatteryNum(0);
            transRecordTemp.setRecessionBicycleNum(0);
            transRecordTemp.setState(0);
            transRecordTemp.setTransFlag(2);
            transRecordTemp.setTransMoney(new BigDecimal(0));
            transRecordTemp.setBicycleType(0);
            transRecordTemp.setSimNo(jsonBikeInfo.get("simNo").toString());
            transRecordTemp.setUseBlueTooth(2);
            transRecordTemp.setUserType(0);
            transRecordTemp.setCityNo(Integer.parseInt(jsonBikeInfo.get("cityNo").toString()));
            transRecordTemp.setUserId(scaveningUnlockRequest.getId());
            transRecordTemp.setEndPosition(scaveningUnlockRequest.getLatitude() + "," + scaveningUnlockRequest.getLongitude());

            TransRecordSupply transRecordSupply = new TransRecordSupply();
            transRecordSupply.setTransId(transRecordOrderId);
            transRecordSupply.setDiscountMoney(new BigDecimal("0"));
            transRecordSupply.setFenceStatus(0);
            transRecordSupply.setOrderFrom("xcx");


            userInfo.setmBorrowBicycle(4);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            transRecordSupplyMapper.insert(transRecordSupply);
            transRecordTempMapper.insert(transRecordTemp);
            return 1;
        } else {
            return 0;
        }
    }

    private int openLockByGprs(ScaveningUnlockRequest scaveningUnlockRequest, JSONObject jsonBikeInfo, UserInfo userInfo, String transRecordOrderId) {
        int res = HttpLockApiUtils.OpenLockByGprs(jsonBikeInfo.get("simNo").toString());
        if (res == 1) {
            //生成订单
            long orderNum = System.currentTimeMillis() * 100 + CommonUtils.getRandom(100);
            TransRecordTemp transRecordTemp = new TransRecordTemp();
            transRecordTemp.setBorrowBicycleNum(Integer.parseInt(jsonBikeInfo.get("bicycleNo").toString()));
            transRecordTemp.setId(transRecordOrderId);
            transRecordTemp.setBorrowBatteryNum(0);
            transRecordTemp.setBorrowDateTime(new Date());
            transRecordTemp.setOrderIntegral(0);
            transRecordTemp.setOrderNum(orderNum);
            transRecordTemp.setRecessionBatteryNum(0);
            transRecordTemp.setRecessionBicycleNum(0);
            transRecordTemp.setState(0);
            transRecordTemp.setTransFlag(2);
            transRecordTemp.setTransMoney(new BigDecimal(0));
            transRecordTemp.setBicycleType(0);
            transRecordTemp.setSimNo(jsonBikeInfo.get("simNo").toString());
            transRecordTemp.setUseBlueTooth(0);
            transRecordTemp.setUserType(0);
            transRecordTemp.setCityNo(Integer.parseInt(jsonBikeInfo.get("cityNo").toString()));
            transRecordTemp.setUserId(scaveningUnlockRequest.getId());

            TransRecordSupply transRecordSupply = new TransRecordSupply();
            transRecordSupply.setTransId(transRecordOrderId);
            transRecordSupply.setDiscountMoney(new BigDecimal("0"));
            transRecordSupply.setFenceStatus(0);
            transRecordSupply.setOrderFrom("xcx");
            transRecordSupply.setUpdateTime(new Date());


            userInfo.setmBorrowBicycle(4);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            transRecordSupplyMapper.insert(transRecordSupply);
            transRecordTempMapper.insert(transRecordTemp);
            return 1;
        } else {
            return 0;
        }
    }
}
