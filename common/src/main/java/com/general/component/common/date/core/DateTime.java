package com.general.component.common.date.core;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期核型
 *
 * @author littlesnail
 * @create 2023−03-31 2:40 PM
 * @since 0.5.0
 */
public class DateTime extends Date {

    /**
     * 构造
     */
    public DateTime() {
    }

    /**
     * 构造
     *
     * @param dateStr Date字符串
     * @param format  格式
     */
    public DateTime(CharSequence dateStr, String format) {
        super(parse(dateStr, new FastDateFormat(format)).getTime());
    }


    /**
     * 转换字符串为Date
     *
     * @param dateStr        日期字符串
     * @param fastDateFormat {@link FastDateFormat}
     * @return {@link Date}
     */
    private static Date parse(CharSequence dateStr, FastDateFormat fastDateFormat) {
        try {
            return fastDateFormat.parse(dateStr.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 格式化日期时间<br>
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public String format(Date date, String format) {
        if (null == date) {
            return null;
        }
        return new FastDateFormat(format).format(date);
    }


    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public Date offsetSecond(Date date, int offset) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, offset);
        date.setTime(cal.getTimeInMillis());
        return date;
    }


    /**
     * 获取日期之间的差距
     *
     * @param startDate 开始
     * @param endDate   结束
     * @param type      日期类型 Calendar.DATE
     * @param format    日期格式
     * @return 数组
     */
    public List<String> getTimeBetweenTime(String startDate,
                                           String endDate,
                                           int type,
                                           String format) {
        return new FastDateFormat(format).getTimeBetweenTime(startDate, endDate, type);
    }
}
