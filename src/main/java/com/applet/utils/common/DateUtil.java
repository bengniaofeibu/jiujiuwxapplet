package com.applet.utils.common;


import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public abstract class DateUtil {


    /**
     * 是否超过指定时间
     *
     * @param startTime
     * @param endTime
     * @param timeFlag
     * @return
     */
    public static boolean isEAppointTime(Date startTime, Date endTime, int timeFlag,TimeType timeType) {

        //开始时间
        long sTimeHour = conversionTime(startTime.getTime(),timeType);

        //结束时间
        long eTimeHour = conversionTime(endTime.getTime(),timeType);

        return (eTimeHour-sTimeHour)>timeFlag;
    }

    /**
     * 毫秒转换时间（秒，分，小时）
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static long conversionTime(long timeStamp, TimeType timeType) {

        long time=0L;
        switch (timeType.getType()) {
            case 1:
                time = (timeStamp % 60000 ) / 1000;
                break;
            case 2:
                time = (timeStamp % 3600000) / 60000;
                break;
            case 3:
                time = timeStamp / 3600000;
                break;
        }
        return time;
    }

    //获取本周一的日期
    public static String getMondayDate(){
        DateTime dateTime=DateTime.now();
        DateTime dateTime1 = dateTime.withDayOfWeek(1).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime1.toDate());
    }

    public enum TimeType {
        SECONDS(1), MINUTES(2), HOURS(3);

        int type;

        TimeType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
