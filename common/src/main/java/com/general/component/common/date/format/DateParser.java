package com.general.component.common.date.format;

import java.text.ParseException;
import java.util.Date;

/**
 * 时间转换 string 转换 date
 *
 * @author littlesnail
 * @version 0.5.0
 * @create 2023−03-31 11:05 AM
 */
public interface DateParser extends DateBase {

    /**
     * 将日期字符串解析并转换为  {@link Date} 对象<br>
     *
     * @param source 日期字符串
     * @return {@link Date}
     * @throws ParseException 转换异常，被转换的字符串格式错误。
     */
    Date parse(String source) throws ParseException;
}
