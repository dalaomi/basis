package com.creditease.async.model;


import java.util.Date;


public class AsyncJob{
	/**
	 */
	private String id;
	private String jobClass;
	private String jobParams;
	private Integer jobType;//0:普通任务；1:定时任务
	private Integer status;//0:未执行；1：成功；2：失败;3:异常暂停
	private String demo;
	private Date createTime;
	private Date updateTime;
    private Date runTime;
    private String jobCrontab;
    private Integer jobIndex;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobClass() {
		return jobClass;
	}
	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	public String getJobParams() {
		return jobParams;
	}
	public void setJobParams(String jobParams) {
		this.jobParams = jobParams;
	}
	
	public Integer getJobType() {
		return jobType;
	}
	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }
    
    

    public String getJobCrontab() {
		return jobCrontab;
	}
	public void setJobCrontab(String jobCrontab) {
		this.jobCrontab = jobCrontab;
	}
	public Integer getJobIndex() {
		return jobIndex;
	}
	public void setJobIndex(Integer jobIndex) {
		this.jobIndex = jobIndex;
	}
	@Override
	public String toString() {
		return "AsyncJob [jobClass=" + jobClass + ", jobParams=" + jobParams
				+ ", jobType=" + jobType + ", status=" + status + "]";
	}
	
	
	

}
