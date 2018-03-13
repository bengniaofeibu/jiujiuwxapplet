package com.applet.mapper;

import com.applet.model.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SysDictMapper {
    List<SysDict> selectLabelByType(String type);
}