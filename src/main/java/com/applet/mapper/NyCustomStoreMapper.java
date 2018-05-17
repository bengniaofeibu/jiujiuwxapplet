package com.applet.mapper;


import com.applet.model.NyCustomStore;

public interface NyCustomStoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(NyCustomStore record);

    int insertSelective(NyCustomStore record);

    NyCustomStore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NyCustomStore record);

    int updateByPrimaryKey(NyCustomStore record);
}