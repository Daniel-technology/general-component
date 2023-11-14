package com.general.component.common.date.format;


import com.general.component.common.exception.CustomException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换 date 转换 str 实现
 *
 * @author littlesnail
 * @create 2023−03-31 2:05 PM
 * @since 0.5.0
 */
public class FastDatePrinter implements DatePrinter {

    /**
     * 日期格式
     */
    private final String pattern;

    public FastDatePrinter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public String format(Date date) {
        if (date == null) {
            throw new CustomException("Date_must_be_not_blank");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);
        return sdf.format(date);
    }
}
