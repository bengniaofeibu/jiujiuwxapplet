package com.applet.schedule;
import com.applet.mapper.JiumiLogMapper;
import com.applet.model.JiumiLog;
import com.applet.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class TimingTask {



    @Autowired
    private UserInfoServiceImpl userInfoService;

    @Autowired
    private JiumiLogMapper jiumiLogMapper;

    //每周星期天凌晨1点执行一次
    @Scheduled(cron = "0 0 1 ? * SUN")
    public void recordUserWeekRankingList(){
        List<String> userIds = userInfoService.getJiumiLogs(0).stream().map(x->x.getUserId()).limit(3).collect(Collectors.toList());

           long jmNum = 20;
           int noun = 1;
        for (String userId:userIds){
            //添加新用户注册赳米记录
            jiumiLogMapper.insertJiuMiLog(new JiumiLog(userId, 20, jmNum, 0, "周排行榜第"+noun));
            jmNum-=5;
            noun+=1;
        }
    }
}
