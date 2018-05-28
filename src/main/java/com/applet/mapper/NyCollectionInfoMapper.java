package com.applet.mapper;

import com.applet.model.NyCollectionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NyCollectionInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(NyCollectionInfo record);

    int insertSelective(NyCollectionInfo record);

    NyCollectionInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NyCollectionInfo record);

    int updateByPrimaryKey(NyCollectionInfo record);

    int getCountNyCollectionInfoByUserId(@Param("userId")String userId);
}