package com.general.component.common.date.format;

import com.general.component.common.exception.CustomException;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换 string 转换 date 实现
 *
 * @author littlesnail
 * @create 2023−03-31 11:05 AM
 * @since 0.5.0
 */
public class FastDateParser implements DateParser {

    /**
     * 日期格式
     */
    private final String pattern;

    public FastDateParser(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }

    @Override
    public Date parse(String source) throws ParseException {
        if (StringUtils.isBlank(source)) {
            throw new CustomException("Date_String_must_be_not_blank");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);
        return sdf.parse(source);
    }


}
