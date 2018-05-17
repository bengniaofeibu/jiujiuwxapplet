package com.applet.mapper;

import com.applet.model.TransRecordTemp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TransRecordTempMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransRecordTemp record);

    int insertSelective(TransRecordTemp record);

    TransRecordTemp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransRecordTemp record);

    int updateByPrimaryKey(TransRecordTemp record);

    int updateByUserIdAndBorrowFlag(TransRecordTemp record);

    TransRecordTemp selectByUserIdAndTransFlag(String userId);

    /**
     * 获取用户骑行时间
     * @param userId
     * @return
     */
    String selectEndTimeByUserIdAndTransFlag(@Param("userId") String userId);
}