package com.general.component.common.system;

/**
 * 内存工具
 *
 * @author littlesnail
 * @create 2023−05-05 10:00 AM
 * @since 0.6.0
 */
public class Memory {

    /**
     * 获得JVM中剩余的内存数，单位byte
     *
     * @return JVM中剩余的内存数，单位byte
     * @since 0.6.0
     */
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 获得JVM已经从系统中获取到的总共的内存数，单位byte
     *
     * @return JVM中剩余的内存数，单位byte
     * @since 0.6.0
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获得JVM中可以从系统中获取的最大的内存数，单位byte，以-Xmx参数为准
     *
     * @return JVM中剩余的内存数，单位byte
     * @since 0.6.0
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获得JVM最大可用内存，计算方法为：<br>
     * 最大内存-总内存+剩余内存
     *
     * @return 最大可用内存
     * @since 0.6.0
     */
    public static long getUsableMemory() {
        return getMaxMemory() - getTotalMemory() + getFreeMemory();
    }
}
