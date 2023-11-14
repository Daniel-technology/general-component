package com.general.component.common.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @description: 随机
 * @author: littlesnail
 * @create: 2023−02-24 10:02 AM
 */
public class RandomUtil {
    public static final String BASE_NUMBER = "0123456789";
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    public static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

    public RandomUtil() {
    }


    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 随机指定范围的数据，[)
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(randomInt(1, 10));
        }
    }

}
