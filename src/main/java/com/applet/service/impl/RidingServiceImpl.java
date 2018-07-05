package com.applet.service.impl;

import com.applet.annotation.SystemServerLog;
import com.applet.entity.LockRequest.CyclingEndInfoRequest;
import com.applet.entity.LockRequest.EndOrderRequest;
import com.applet.entity.LockRequest.GeoCacheReq;
import com.applet.entity.LockRequest.QueryRidingStatusRequest;
import com.applet.entity.LockResponse.CyclingEndInfoResponse;
import com.applet.entity.LockResponse.QueryRidingStatusResponse;
import com.applet.model.NyCustomStore;
import com.applet.entity.geo.BikeGeoInfo;
import com.applet.entity.geo.GeoInfo;
import com.applet.enums.ResultEnums;
import com.applet.mapper.FeedbackInfoMapper;
import com.applet.mapper.SysDictMapper;
import com.applet.mapper.TransRecordTempMapper;
import com.applet.mapper.UserInfoMapper;
import com.applet.model.*;
import com.applet.service.RidingService;
import com.applet.service.StoreCacheService;
import com.applet.utils.AppletResult;
import com.applet.utils.ResultUtil;
import com.applet.utils.ReturnDataUtil;
import com.applet.utils.common.CommonUtils;
import com.applet.utils.common.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RidingServiceImpl implements RidingService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private TransRecordTempMapper transRecordTempMapper;

    @Autowired
    private FeedbackInfoMapper feedbackInfoMapper;

    @Autowired
    private SysDictMapper sysDictMapper;

    @Autowired
    private StoreCacheService storeCacheService;

    private static final String STORE_POINT_KEY = "store:info:point:";

    private static final Logger LOGGER = LoggerFactory.getLogger(RidingServiceImpl.class);

    @Override
    @SystemServerLog(funcionExplain = "查询骑行状态")
    public AppletResult queryRidingStatus(QueryRidingStatusRequest queryRidingStatusRequest) {
        UserInfo userInfo = userInfoMapper.selectUserInfoById(queryRidingStatusRequest.getId());
        if (userInfo != null) {
            QueryRidingStatusResponse queryRidingStatusResponse = new QueryRidingStatusResponse();
            queryRidingStatusResponse.setRidingFlag(userInfo.getmBorrowBicycle());
            Date currentTime = new Date();
            long ridingTime;
            if (userInfo.getmBorrowBicycleDate() != null) {
                if (currentTime.getTime() > userInfo.getmBorrowBicycleDate().getTime()) {
                    ridingTime = (currentTime.getTime() - userInfo.getmBorrowBicycleDate().getTime()) / 1000 / 60;
                } else {
                    ridingTime = 0;
                }
            } else {
                ridingTime = 0;
            }
            queryRidingStatusResponse.setRidingTime(ridingTime);
            queryRidingStatusResponse.setRidingCost(0);
            return ResultUtil.success(queryRidingStatusResponse);
        } else {
            return ResultUtil.error(ResultEnums.INVALID_USER);
        }
    }

    @Override
    @SystemServerLog(funcionExplain = "故障报修")
    public AppletResult endOrder(EndOrderRequest endOrderRequest) {
        UserInfo userInfo = userInfoMapper.selectUserInfoById(endOrderRequest.getId());
        if (userInfo != null) {
            String uuid = UUID.randomUUID().toString();
            FeedbackInfo feedbackInfo = new FeedbackInfo();
            String bicycleNo;
            if (("1".equals(endOrderRequest.getSonType()) || "9".equals(endOrderRequest.getSonType())) && endOrderRequest.getType() == 2) {
                TransRecordTemp transRecordTemp = transRecordTempMapper.selectByUserIdAndTransFlag(endOrderRequest.getId());
                if (transRecordTemp != null) {
                    transRecordTemp.setUserId(endOrderRequest.getId());
                    transRecordTemp.setTransFlag(1);
                    transRecordTemp.setState(1);
                    transRecordTemp.setRecessionDateTime(new Date());
                    transRecordTempMapper.updateByUserIdAndBorrowFlag(transRecordTemp);
                    userInfo.setmBorrowBicycle(0);
                    userInfo.setId(endOrderRequest.getId());
                    userInfoMapper.updateBorrowFlagById(userInfo);
                    feedbackInfo.setTransId(transRecordTemp.getId());
                    bicycleNo = String.valueOf(transRecordTemp.getBorrowBicycleNum());
                } else {
                    return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_ERRORTRANSRECORD);
                }
            } else {
                bicycleNo = CommonUtils.DecodeBarcode(endOrderRequest.getBarcode());
            }

            if (!bicycleNo.equals("0")) {
                feedbackInfo.setId(uuid);
                feedbackInfo.setAddtime(new Date());
                feedbackInfo.setBicycleNum(Integer.parseInt(bicycleNo));
                feedbackInfo.setDescription(endOrderRequest.getDescription());
                feedbackInfo.setPicurl(endOrderRequest.getPicurl());
                feedbackInfo.setSonType(endOrderRequest.getSonType());
                feedbackInfo.setType(endOrderRequest.getType());
                feedbackInfo.setUserId(endOrderRequest.getId());
                feedbackInfo.setPlatform(3);
                feedbackInfo.setOpenId(endOrderRequest.getOpenId());
                if (!CommonUtils.isEmptyString(endOrderRequest.getLongitude())) {
                    feedbackInfo.setBikeLng(endOrderRequest.getLongitude());
                }
                if (!CommonUtils.isEmptyString(endOrderRequest.getLatitude())) {
                    feedbackInfo.setBikeLat(endOrderRequest.getLatitude());
                }
                feedbackInfoMapper.insert(feedbackInfo);
                return ResultUtil.success();
            } else {
                return ResultUtil.error(ResultEnums.SCAVENING_UNLOCK_ERRORBARCODE);
            }
        } else {
            return ResultUtil.error(ResultEnums.INVALID_USER);
        }
    }

    /**
     * 获取故障报修列表
     *
     * @param type 报修类型
     * @return
     */
    @SystemServerLog(funcionExplain = "获取故障保修列表")
    @Override
    public List<SysDict> getFailureWarrantyList(String type) {
        LOGGER.debug("type {}", type);

        List<SysDict> sysDicts = sysDictMapper.selectLabelByType(type);
        return sysDicts;
    }

    /**
     * 获取骑行结束后的信息
     *
     * @param request
     * @return
     */
    @SystemServerLog(funcionExplain = "获取骑行结束后的信息")
    @Override
    public AppletResult queryCyclingEndInfo(CyclingEndInfoRequest request) {


        String endTime = transRecordTempMapper.selectEndTimeByUserIdAndTransFlag(request.getUserId());
        LOGGER.debug(" 用户骑行时间 {}",endTime);

        GeoCacheReq geoCacheReq = new GeoCacheReq();
        geoCacheReq.setKey(STORE_POINT_KEY);
        geoCacheReq.setLat(Double.valueOf(request.getLatitude()));
        geoCacheReq.setLng(Double.valueOf(request.getLongitude()));
        geoCacheReq.setLimit(20);//条数
        geoCacheReq.setDirectionType(0);
        geoCacheReq.setDistanceType(0);
        geoCacheReq.setDistance(10000);//距离



        //获取所有周围门店信息
        AppletResult appletResult = storeCacheService.radiusGeo(geoCacheReq);
        LOGGER.debug("appletResult {}", appletResult);

        if (appletResult != null) {
            ReturnDataUtil returnDataUtil = JSONUtil.parseObject(JSONUtil.toJSONString(appletResult.getData()), ReturnDataUtil.class);
            if (returnDataUtil != null) {
                GeoInfo geoInfo = JSONUtil.parseObject(JSONUtil.toJSONString(returnDataUtil.getReturnData()), GeoInfo.class);
                LOGGER.debug("geoinfo {}", geoInfo);
                if (geoInfo !=null ){
                    List<BikeGeoInfo> bikeGeoInfoList = geoInfo.getResults();
                    List<NyCustomStore> nyCustomStores = new LinkedList<>();
                    if (bikeGeoInfoList != null || bikeGeoInfoList.size() > 0) {
                        for (BikeGeoInfo bikeGeoInfo : bikeGeoInfoList) {
                            //获取指定门店信息
                            AppletResult storeResult = storeCacheService.queryStoreCacheInfo(bikeGeoInfo.getContent().getName());
                            if (storeResult != null) {
                                ReturnDataUtil storeDataUtil = JSONUtil.parseObject(JSONUtil.toJSONString(storeResult.getData()), ReturnDataUtil.class);
                                NyCustomStore storeInfo;
                                if (storeDataUtil != null && storeDataUtil.getReturnData() != null) {
                                    NyCustomStore nyCustomStore = JSONUtil.parseObject(JSONUtil.toJSONString(storeDataUtil.getReturnData()), NyCustomStore.class);
                                    if (nyCustomStore != null) {
                                        storeInfo = new NyCustomStore();
                                        storeInfo.setId(nyCustomStore.getId());
                                        storeInfo.setTitle(nyCustomStore.getTitle());
                                        storeInfo.setStoreAddr(nyCustomStore.getStoreAddr());
                                        storeInfo.setStorePic1(nyCustomStore.getStorePic1());
                                        storeInfo.setDistance(new BigDecimal(bikeGeoInfo.getDistance().getValue()).intValue());
                                        storeInfo.setCustomType(nyCustomStore.getCustomType());
                                        nyCustomStores.add(storeInfo);
                                    }
                                }
                            }
                        }
                        //过滤出探店门店信息
                        List<NyCustomStore> agentShop = nyCustomStores.stream().filter((x) -> x.getCustomType().equals(1)).limit(1).collect(Collectors.toList());

                        //过滤出早餐门店信息
                        List<NyCustomStore> breakFast = nyCustomStores.stream().filter((x) -> x.getCustomType().equals(3)).limit(1).collect(Collectors.toList());
                        LOGGER.debug("agentShop {}  breakFast {}",agentShop,breakFast);

                        agentShop.addAll(breakFast);

                        return ResultUtil.success(new CyclingEndInfoResponse(endTime,agentShop));
                    }
                }
            }

        }
        return ResultUtil.error(ResultEnums.DATA_NOT_FOUND);
    }
}