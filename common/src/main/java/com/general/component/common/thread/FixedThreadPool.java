package com.general.component.common.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定长的线程池，阻塞的固定大小的线程池
 *
 * @author Daniel
 * @date 2023/11/10
 */
public class FixedThreadPool extends ThreadPoolExecutor {

    public FixedThreadPool(int size) {
        super(size, size, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ExecutorThreadFactory("fixed"));
    }

    public FixedThreadPool(int size, ThreadFactory threadFactory) {
        super(size, size, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
    }

    /**
     * 执行任务方法
     *
     * @param command 线程
     *                当线程池中没有空闲线程时, 会挂起此方法的调用线程.直到线程池中有线程有空闲线程.
     * @date 2018年12月27日 上午9:05:29
     */
    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }


}
