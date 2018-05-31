package com.applet.mapper;

import com.applet.model.LoginDailyLog;
import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface LoginDailyLogMapper {

    long selectUserCurdateCountById(@Param("userId") String userId);

    int insertUserLoginRecord(LoginDailyLog record);
}