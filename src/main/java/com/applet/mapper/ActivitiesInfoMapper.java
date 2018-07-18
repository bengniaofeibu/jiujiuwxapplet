package com.applet.mapper;

import com.applet.model.ActivitiesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ActivitiesInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ActivitiesInfo record);

    int insertSelective(ActivitiesInfo record);

    ActivitiesInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivitiesInfo record);

    int updateByPrimaryKey(ActivitiesInfo record);
}