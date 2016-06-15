package com.mdy.async.job;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.mdy.async.model.AsyncJob;
import com.mdy.async.model.JobResult;
import com.mdy.async.thread.ILifeCycle;
import com.mdy.async.util.JsonUtils;

/**
 * 抽象job类。
 */
public abstract class AbstractJobExecutor implements Runnable, ILifeCycle {
	private final Logger logger = Logger.getLogger(getClass());
    protected String _lineSeparator = System.getProperty("line.separator"); 
    protected AsyncJob asyncJob;
    protected Map<String, Object> paramsMap = new HashMap<String,Object>();
    
    @Override
    public void init() {
    }
    
    @Override
    public void run() {
    	JobResult taskResult = new JobResult();
    	try {
    		checkParams();
    		logger.info("async_job | "+asyncJob.getId()+" | 开始任务 ｜ params="+paramsMap);
    		taskResult = execute();
		} catch (Exception e) {
			logger.error("async_job | 任务异常 ｜ params="+paramsMap+",e="+e);
			taskResult.setCode("FAIL");
			taskResult.setMessage(e.getMessage());
		}finally {
			callBack(taskResult);
		}
    	logger.info("async_job | "+asyncJob.getId()+" | 结束任务 ｜ params="+paramsMap+",taskResult="+taskResult);
    }
    
    
    private void checkParams(){
    	if(asyncJob == null){
    		logger.error("async_job | 任务异常-参数异常－异步任务为空");
    		throw new IllegalArgumentException("任务异常-参数异常－异步任务为空");
    	}
    	if(StringUtils.isNotBlank(asyncJob.getJobParams())){
    		decodeParams(asyncJob.getJobParams());
    	}
//    	if(asyncJob.getJobType() == null){
//    		
//    	}
    	
    }
    
    
    
    protected abstract JobResult execute();
    protected abstract void callBack(JobResult taskResult);
    
    @Override
    public void destroy() {
    }
    
    
    
    
    public void decodeParams(String jsonParams){
    	try {
    		paramsMap = JsonUtils.toMapObject(jsonParams);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("async_job | 任务异常-参数异常－解析参数Map异常");
    		throw new IllegalArgumentException("任务异常-参数异常－解析参数Map异常");
		}
    	
    }
    public String encodeParams(Map<String, Object> paramsMap){
    	return JsonUtils.toJsonString(paramsMap);
    }
    
    
    public void setAsyncJob(AsyncJob asyncJob) {
		this.asyncJob = asyncJob;
	}

}
