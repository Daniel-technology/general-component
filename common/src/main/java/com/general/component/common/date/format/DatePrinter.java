package com.general.component.common.date.format;

import java.util.Date;

/**
 * 时间输出 date 输出为string
 *
 * @author littlesnail
 * @since 0.5.0
 * @create 2023−03-31 11:06 AM
 */
public interface DatePrinter extends DateBase {


    /**
     * 使用 {@code GregorianCalendar} 格式化 {@code Date}
     *
     * @param date 日期 {@link Date}
     * @return 格式化后的字符串
     */
    String format(Date date);
}
