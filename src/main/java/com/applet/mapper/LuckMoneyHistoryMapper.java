package com.applet.mapper;

import com.applet.model.LuckMoneyHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface LuckMoneyHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(LuckMoneyHistory record);

    int insertSelective(LuckMoneyHistory record);

    LuckMoneyHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LuckMoneyHistory record);

    int updateByPrimaryKey(LuckMoneyHistory record);

    List<LuckMoneyHistory> getTodayLuckyMoneyHistoryInfos(@Param("addTime") String date, @Param("userId") String userId);
}