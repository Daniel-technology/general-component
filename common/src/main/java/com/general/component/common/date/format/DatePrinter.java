package com.general.component.common.date.format;

import java.util.Date;
import java.util.List;

/**
 * 时间输出 date 输出为string
 *
 * @author littlesnail
 * @create 2023−03-31 11:06 AM
 * @since 0.5.0
 */
public interface DatePrinter extends DateBase {


    /**
     * 使用 {@code GregorianCalendar} 格式化 {@code Date}
     *
     * @param date 日期 {@link Date}
     * @return 格式化后的字符串
     */
    String format(Date date);

    /**
     * 获取日期之间的差距
     *
     * @param startDate 开始
     * @param endDate   结束
     * @param type      Calendar.DATE
     * @return 数组
     */
    List<String> getTimeBetweenTime(String startDate,
                                    String endDate,
                                    int type);

}
