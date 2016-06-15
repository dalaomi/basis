package com.mdy.async.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.mdy.async.EaseConsumer;
import com.mdy.async.job.AbstractJobExecutor;
import com.mdy.async.model.AsyncJob;
import com.mdy.async.thread.ThreadPool;

/**
 * 多线程池调用代码示例。
 * 
 */
public class ThreadPoolExample2 {
    
    public static void main(String[] args) throws Exception {
    	
    	//模拟从库获取任务列表
    	List<AsyncJob> asyncJobs = new ArrayList<AsyncJob>();
    	for(int i = 0;i<1;i++){
    		AsyncJob asyncJob = new AsyncJob();
    		asyncJob.setId(i+"");
    		asyncJob.setJobClass("com.mdy.async.sample.DemoJobExecutor");
    		asyncJob.setJobParams("{param:param1}");
    		asyncJob.setCreateTime(new Date());
    		asyncJob.setUpdateTime(new Date());
    		asyncJobs.add(asyncJob);
    	}
    	
    	for(AsyncJob asyncJob:asyncJobs){
    		EaseConsumer.getInstance().init();
    		AbstractJobExecutor abstractJobExecutor = (AbstractJobExecutor) Class.forName(asyncJob.getJobClass()).newInstance();
    		System.out.println(abstractJobExecutor);
    		abstractJobExecutor.setAsyncJob(asyncJob);
    		EaseConsumer.getInstance().submit(abstractJobExecutor);
    	}
    }

    /**
     * 执行不需要返回值的异步任务。
     */
    private static void executeRunnableAnsyTask(ThreadPool threadPool)
            throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            threadPool.submit(new RunnableAsynTask());
            threadPool.submit(new RunnableAsynTask(), "other");
            
            Thread.sleep(50);
        }
    }
    
    /**
     * 执行需要返回值的异步任务。
     */
    private static void executeCallableAnsyTask(ThreadPool threadPool)
            throws InterruptedException, ExecutionException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        CallableAnsyTask task = new CallableAnsyTask(arr);
        Future<Long> future = threadPool.submit(task);
        System.out.println("异步任务在线程池default的执行结果为:"+future.get());
        threadPool.submit(task, "other");
        System.out.println("异步任务在线程池other的执行结果为:"+future.get());
    }

    /**
     * 并行调用多个异步任务。
     */
    private static void parallelExecuteAnsyTask(ThreadPool threadPool) 
            throws InterruptedException, ExecutionException {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
        tasks.add(new CallableAnsyTask(arr));
        tasks.add(new CallableAnsyTask(arr));
        tasks.add(new CallableAnsyTask(arr));
        
        List<Future<Long>> futures = threadPool.invokeAll(tasks, 1, TimeUnit.SECONDS);
        for (Future<Long> future : futures) {
            Long result = future.get();   // 如果某个任务执行超时，调用该任务对应的future.get时抛出CancellationException异常
            System.out.println("并行调用，其中一个任务的执行结果为:"+result);
        }
        
    }

}
