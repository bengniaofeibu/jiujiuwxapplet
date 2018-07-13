package com.applet.schedule;

import com.applet.mapper.JiumiLogMapper;
import com.applet.model.JiumiLog;
import com.applet.service.impl.UserInfoServiceImpl;
import com.applet.utils.common.CommonUtils;
import com.applet.utils.common.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Comparator;
import java.util.List;

@Configuration
@EnableScheduling
public class TimingTask {

    //每天凌晨1点执行
    @Scheduled(cron = "0 0 1 * * *")
    public void recordUserTotalJiuMiRankIngList(){
    }
}
