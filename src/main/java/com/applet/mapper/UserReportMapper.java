package com.applet.mapper;

import com.applet.model.UserReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserReportMapper {

    int insertUserReportInfo(UserReport record);

    int selectUserIdByUserId(String userId);
}