package com.general.component.common.utils;

import java.util.Set;
import java.util.TreeMap;

/**
 * @description: 彩票
 * @author: littlesnail
 * @create: 2023−02-24 9:48 AM
 */
public class LotteryUtil {


    /**
     * 双色球红色最小值
     */
    private static final int RED_MIN_NUM = 1;
    /**
     * 双色球红色最大值
     */
    private static final int RED_MAX_NUM = 33;

    /**
     * 双色球红色个数
     */
    private static final int RED_SIZE = 6;

    /**
     * 双色球蓝色最小值
     */
    private static final int BLUE_MIN_NUM = 1;
    /**
     * 双色球蓝色最大值
     */
    private static final int BLUE_MAX_NUM = 16;

    /**
     * 双色球蓝色个数
     */
    private static final int BLUE_SIZE = 1;

    /**
     * 生成双色球数据
     */
    public static void calBicolorSphere() {
        TreeMap<Integer, Integer> redTreeMap = new TreeMap<>();
        //生成红色球数据
        for (int i = 0; i < 100; i++) {
            if (redTreeMap.size() == RED_SIZE) {
                break;
            }
            int redRandomNum = RandomUtil.randomInt(RED_MIN_NUM, RED_MAX_NUM);
            redTreeMap.put(redRandomNum, redRandomNum);
        }
        Set<Integer> set = redTreeMap.keySet();
        StringBuilder msg = new StringBuilder();
        for (Integer temp : set) {
            msg.append(" ").append(temp);
        }
        int blueRandomNum = RandomUtil.randomInt(BLUE_MIN_NUM, BLUE_MAX_NUM);
        msg.append(" - ").append(blueRandomNum);
        System.out.println("双色球彩票信息：" + msg);
    }

    public static void main(String[] args) {
        calBicolorSphere();
    }

}
