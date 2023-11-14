package com.general.component.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author Daniel
 * @date 2023/11/10
 */
public class ExecutorThreadFactory implements ThreadFactory {
    /**
     * POOL_NUMBER
     */
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);

    /**
     * group
     */
    private final ThreadGroup group;

    /**
     * threadNumber
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * namePrefix
     */
    private final String namePrefix;

    /**
     * ScheduledThreadFactory()
     */
    ExecutorThreadFactory(String threadPoolName) {
        group = Thread.currentThread().getThreadGroup();
        namePrefix = threadPoolName + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
