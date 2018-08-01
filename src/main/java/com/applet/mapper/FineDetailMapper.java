package com.applet.mapper;

import com.applet.model.FineDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FineDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(FineDetail record);

    int insertSelective(FineDetail record);

    int selectCountByUserIdAndStatus(@Param("userId") String userId);

    int updateByPrimaryKeySelective(FineDetail record);

    int updateByPrimaryKey(FineDetail record);
}