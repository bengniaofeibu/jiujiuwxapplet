package com.applet.mapper;

import com.applet.model.LuckyMoneyDailyParameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface LuckyMoneyDailyParameterMapper {
    int deleteByPrimaryKey(String id);

    int insert(LuckyMoneyDailyParameter record);

    int insertSelective(LuckyMoneyDailyParameter record);

    LuckyMoneyDailyParameter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LuckyMoneyDailyParameter record);

    int updateByPrimaryKey(LuckyMoneyDailyParameter record);

    LuckyMoneyDailyParameter getLuckyMoneyActivityInfo(String day);
}