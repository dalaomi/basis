package com.mdy.async;

import com.mdy.async.job.AbstractJobExecutor;
import com.mdy.async.thread.ThreadPoolImpl;

public class EaseConsumer {
	private static EaseConsumer taskManager = new EaseConsumer();
	private static byte[] lock = new byte[0];
	private static boolean init = false;
	private ThreadPoolImpl threadPoolImpl;
	public static EaseConsumer getInstance(){
		return taskManager;
	}
	
	
	public void init(){
		synchronized (lock) {
			if(init)
				return;
			if(threadPoolImpl == null){
				threadPoolImpl = new ThreadPoolImpl();
				threadPoolImpl.init();
			}
		}
	}
	
	
	public void submit(AbstractJobExecutor job){
		submit(job, ThreadPoolImpl.DEFAULT_THREAD_POOL);
	}
	
	
	public void submit(AbstractJobExecutor job,String threadpoolName){
		if(threadPoolImpl!=null){
			threadPoolImpl.submit(job,threadpoolName);
		}else {
			throw new RuntimeException("线程组尚未初始化");
		}
	}
	
	
	
	public void destroy(){
		threadPoolImpl.destroy();
	}
	private EaseConsumer(){}
}
