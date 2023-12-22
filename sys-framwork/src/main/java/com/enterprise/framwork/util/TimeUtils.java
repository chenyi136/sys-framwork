package com.enterprise.framwork.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;

/**
 * @author chenyi
 */
public class TimeUtils {
    private static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";

    private static final String STANDARD_FORMAT_DATE="yyyy-MM-dd";

    public static String dateToStr(Date date){
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static String dateToStrNoTime(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }


    // 当前时间转换为utc格式
    public static String stringFormatToUtc(DateTime dateTime) {
        return dateTime.toString("yyyy-MM-dd'T'HH:mm:ss'Z'");
    }

    // 时间大小比较
    public static String latterTime(String time1, String time2) {
        if (StringUtils.isBlank(time1)) return time2;
        if (StringUtils.isBlank(time2)) return time1;
        Instant instant1 = Instant.parse(time1);
        Instant instant2 = Instant.parse(time2);
        if (instant1.compareTo(instant2) < 0) {
           return time2;
        } else  {
           return time1;
        }
    }

    // 计算两个时间的差距天数
    public static long daysBetween(String utcTime1, String utcTime2) {
        LocalDateTime time1 = LocalDateTime.parse(utcTime1, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime time2 = LocalDateTime.parse(utcTime2, DateTimeFormatter.ISO_DATE_TIME);
        long daysBetween = Math.abs(time1.toEpochSecond(ZoneOffset.UTC) - time2.toEpochSecond(ZoneOffset.UTC)) / (24 * 60 * 60);
        return daysBetween;
    }

    public static String timeFormat(DateTime dateTime, String format) {
        return dateTime.toString(format);
    }

    // 时间融合
    public static String[] mergeLastSeenAndFirstSeen(String oldLastSeen, String newLastSeen,
                                       String oldFirstSeen, String newFirstSeen) {
        String[] time = new String[2];
        // lastSeen融合
        // 都有值取时间晚的
        if (StringUtils.isNotBlank(oldLastSeen) &&
                StringUtils.isNotBlank(newLastSeen)) {
            String latterTime = TimeUtils.latterTime(oldFirstSeen, newLastSeen);
            if (latterTime.equals(newLastSeen)) {
                time[0] = newLastSeen;
            } else {
                time[0] = oldLastSeen;
            }
            // 都无值，取现在时间
        } else if (StringUtils.isBlank(oldLastSeen) &&
                StringUtils.isBlank(newLastSeen) ) {
            DateTime now = DateTime.now(DateTimeZone.UTC);
            time[0] = stringFormatToUtc(now);
        } else if (StringUtils.isBlank(oldFirstSeen)) {
            time[0] = newLastSeen;
        }

        // firstSeen 融合
        if (StringUtils.isNotBlank(oldFirstSeen) &&
                StringUtils.isNotBlank(newFirstSeen)) {
            String latterTime = TimeUtils.latterTime(oldFirstSeen, newFirstSeen);
            // 新数据更早
            if (latterTime.equals(oldFirstSeen)) {
                // 晚于lastSeen
                if (TimeUtils.latterTime(latterTime, oldLastSeen).equals(latterTime)) {
                    time[1] = oldLastSeen;
                } else {
                    time[1] = newFirstSeen;
                }
            }
        } else if (StringUtils.isBlank(oldFirstSeen) &&
                StringUtils.isBlank(newFirstSeen)) {
            DateTime now = DateTime.now(DateTimeZone.UTC);
            time[1] = stringFormatToUtc(now);
        } else if (StringUtils.isBlank(oldFirstSeen)) {
//            location1.setLastSeen(location2.getLastSeen());
            time[1] = newFirstSeen;
        }
        return time;
    }
}
