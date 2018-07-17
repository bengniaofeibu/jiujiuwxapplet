package com.applet.mapper;

import com.applet.model.JiumiMission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface JiumiMissionMapper {
    int selectCountByOnOff();
}