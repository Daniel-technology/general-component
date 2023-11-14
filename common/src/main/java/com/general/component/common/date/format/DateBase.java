package com.general.component.common.date.format;

/**
 * 基础接口
 *
 * @author littlesnail
 * @version 0.5.0
 * @create 2023−03-31 11:11 AM
 */
public interface DateBase {
    /**
     * 获得日期格式化或者转换的格式
     *
     * @return {@link java.text.SimpleDateFormat}
     */
    String getPattern();

}
