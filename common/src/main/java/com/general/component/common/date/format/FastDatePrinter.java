package com.general.component.common.date.format;


import com.general.component.common.exception.CustomException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    /**
     * 获取日期之间的差距
     *
     * @param startDate 开始
     * @param endDate   结束
     * @param type      Calendar.DATE
     * @return 数组
     */
    public List<String> getTimeBetweenTime(String startDate,
                                           String endDate,
                                           int type) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            List<String> dates = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(startDate));
            Date endDt = sdf.parse(endDate);
            while (calendar.getTime().before(endDt)) {
                Date result = calendar.getTime();
                SimpleDateFormat sdfTemp = new SimpleDateFormat(pattern);
                String date = sdfTemp.format(result);
                dates.add(date);
                calendar.add(type, 1);
            }
            return dates;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
