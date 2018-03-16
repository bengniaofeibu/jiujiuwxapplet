package com.applet.utils.common;


import org.springframework.stereotype.Component;

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
     * 毫秒转换时间（秒，分，小时，天）
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static long conversionTime(long timeStamp, TimeType timeType) {

        long time=0L;
        switch (timeType.getType()) {
            case 1:
                time = (timeStamp % (1000 * 60)) / 1000;
                break;
            case 2:
                time = (timeStamp % (1000 * 60 * 60)) / (1000 * 60);
                break;
            case 3:
                time = (timeStamp % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                break;
            case 4:
                time = timeStamp / (1000 * 60 * 60 * 24);
                break;
        }
        return time;
    }

    public enum TimeType {
        SECONDS(1), MINUTES(2), HOURS(3), DAYS(4);

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
