package com.cf.storage.common.pool.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * <p>类名称     ：com.fst.common.pool.thread.ThreadPoolMgt</p>
 * <p>描述          ：线程池管理类
 * 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。 
 * 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。 
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。 
 * 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
 * </p>
 * <p>创建人     ：JetGuo</p>
 * <p>创建日期：2017年10月12日</p>
 * <p>修改人     ：</p>
 * <p>修改描述：</p>
 */
public class ThreadPoolMgt {
    /**
     *   corePoolSize： 线程池维护线程的最少数量 
     *   maximumPoolSize：线程池维护线程的最大数量 
     *   keepAliveTime： 线程池维护线程所允许的空闲时间 
     *   unit： 线程池维护线程所允许的空闲时间的单位 
     *   workQueue： 线程池所使用的缓冲队列 
     *   handler： 线程池对拒绝任务的处理策略 
     */
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(200),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    
    public static void addTask(FstTask task){
        threadPool.execute(task);
    }
    
    public static int getCurrentQueueSize(){
        return  threadPool.getPoolSize();
    }
}
