package com.applet.mapper;

import com.applet.model.SysArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SysAreaMapper {

    SysArea selecIdtByCountName(@Param("cityName") String cityName);

}