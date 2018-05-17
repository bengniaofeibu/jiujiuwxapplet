package com.applet.service;

import com.applet.entity.LockRequest.CyclingEndInfoRequest;
import com.applet.entity.LockRequest.EndOrderRequest;
import com.applet.entity.LockRequest.QueryRidingStatusRequest;
import com.applet.model.SysDict;
import com.applet.utils.AppletResult;

import java.util.List;

public interface RidingService {

    AppletResult queryRidingStatus(QueryRidingStatusRequest queryRidingStatusRequest);

    AppletResult endOrder(EndOrderRequest endOrderRequest);

    /**
     * 获取故障报修列表
     * @param type 报修类型
     * @return
     */
    List<SysDict> getFailureWarrantyList(String type);

    /**
     * 获取骑行结束后的信息
     * @param request
     * @return
     */
    AppletResult queryCyclingEndInfo(CyclingEndInfoRequest request);




}
