package com.applet.schedule;

import com.applet.utils.common.CommonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TimingTask {

//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void test(){
//        System.out.println(CommonUtils.getCurrentTimeFormat("yyyy-MM-dd HH:mm:ss") + "定时任务测试");
//    }
}
