package com.applet.mapper;

import com.applet.model.OperateConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OperateConfigMapper {

    /**
     * 根据城市去查骑行免押金时间
     * @param id
     * @return
     */
    OperateConfig selectLimitHourByCity(Integer cityNo);

    /**
     * 根据全国去查骑行免押金时间
     * @return
     */
    OperateConfig selectLimitHourByCountry();
}