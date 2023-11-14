package com.general.component.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间
 *
 * @author littlesnail
 * @create 2023−03-12 11:06 AM
 */
public class DateUtil {


    /**
     * 字符串转date
     *
     * @param str
     * @param sdf
     * @return
     * @throws ParseException
     */
    public static Date dateToStr(String str, SimpleDateFormat sdf) throws ParseException {
        return sdf.parse(str);
    }
}
