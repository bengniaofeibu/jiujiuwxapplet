package com.applet.mapper;

import com.applet.model.JiumiLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface JiumiLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JiumiLog record);

    int insertJiuMiLog(JiumiLog record);

    List<JiumiLog> selectWeekRankingList(@Param("startDate") String startDate);

    JiumiLog selectWeekJiuMiNumByUserId(@Param("userId") String userId,@Param("startDate") String startDate);

    JiumiLog selectTotalJiuMiNumByUserId(@Param("id") String id);

    List<JiumiLog> selectTotalJiuMiNum();

    int updateByPrimaryKeySelective(JiumiLog record);

    int updateByPrimaryKey(JiumiLog record);
}