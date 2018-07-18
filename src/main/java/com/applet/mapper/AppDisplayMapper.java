package com.applet.mapper;

import com.applet.model.AppDisplay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AppDisplayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppDisplay record);

    int insertSelective(AppDisplay record);

    AppDisplay selectByPrimaryKey(Integer id);

    List<AppDisplay> selectPopup(String cityName);

    int updateByPrimaryKeySelective(AppDisplay record);

    int updateByPrimaryKey(AppDisplay record);
}