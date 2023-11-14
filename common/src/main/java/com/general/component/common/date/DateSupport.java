package com.general.component.common.date;


import com.general.component.common.date.core.DateTime;

import java.util.Date;

/**
 * 日期提供
 *
 * @author littlesnail
 * @create 2023−03-31 2:12 PM
 * @since 0.5.0
 */
public class DateSupport {


    /**
     * 根据传入类型进行日期转换
     *
     * @param dateStr 时间
     * @param format  格式
     * @return
     */
    public static Date parse(CharSequence dateStr, String format) {
        return new DateTime(dateStr, format);
    }


    /**
     * date 转换 string
     *
     * @param date   时间
     * @param format 格式
     * @return
     */
    public static String format(Date date, String format) {
        return new DateTime().format(date, format);
    }


    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetSecond(Date date, int offset) {
        return new DateTime().offsetSecond(date, offset);
    }
}
