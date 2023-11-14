package com.general.component.common.date.core;


import com.general.component.common.date.format.DateParser;
import com.general.component.common.date.format.DatePrinter;
import com.general.component.common.date.format.FastDateParser;
import com.general.component.common.date.format.FastDatePrinter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 日期转换
 *
 * @author littlesnail
 * @create 2023−03-31 1:55 PM
 * @since 0.5.0
 */
public class FastDateFormat implements DateParser, DatePrinter {


    /**
     * 转换类
     */
    private final FastDateParser parser;

    private final FastDatePrinter printer;

    /**
     * 日期格式
     */
    private final String pattern;

    public FastDateFormat(String pattern) {
        this.pattern = pattern;
        this.parser = new FastDateParser(pattern);
        this.printer = new FastDatePrinter(pattern);
    }


    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public Date parse(String source) throws ParseException {
        return this.parser.parse(source);
    }

    @Override
    public String format(Date date) {
        return this.printer.format(date);
    }

    /**
     * 获取日期之间的差距
     *
     * @param startDate 开始
     * @param endDate   结束
     * @param type      Calendar.DATE
     * @return 数组
     */
    @Override
    public List<String> getTimeBetweenTime(String startDate, String endDate, int type) {
        return this.printer.getTimeBetweenTime(startDate, endDate, type);
    }


}
