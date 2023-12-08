package com.general.component.common.regex.core;

import java.util.regex.Pattern;

/**
 * 正则
 *
 * @author littlesnail
 * @create 2023−03-27 6:15 PM
 */
public class RegexPatternManager {

    /**
     * 正则匹配
     *
     * @param pattern 表达式
     * @param str     待匹配字符串
     * @return
     */
    public boolean check(Pattern pattern, String str) {
        return pattern.matcher(str).matches();
    }
}
