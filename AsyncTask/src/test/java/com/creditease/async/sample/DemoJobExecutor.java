package com.creditease.async.sample;

import com.creditease.async.job.AbstractJobExecutor;
import com.creditease.async.model.JobResult;

public class DemoJobExecutor extends AbstractJobExecutor{

	@Override
	protected JobResult execute() {
		System.out.println("开始执行DEMO任务");
		try {
			System.out.println("模拟任务执行－10秒");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("结束执行DEMO任务");
		
		return null;
	}

	@Override
	protected void callBack(JobResult taskResult) {
		
		System.out.println("任务执行完成:"+taskResult);
		System.out.println("任务执行完成:"+taskResult.getCode()+",message="+taskResult.getMessage());
	}

}
