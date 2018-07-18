package com.applet.mapper;

import com.applet.model.JiumiSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface JiumiSettingMapper {

    int selectIncValueByMissionId();
}