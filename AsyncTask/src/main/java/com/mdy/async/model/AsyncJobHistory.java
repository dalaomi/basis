package com.mdy.async.model;

import java.util.Date;

public class AsyncJobHistory{

		private String id;
		private java.lang.String jobClass;
		private java.lang.String jobParams;
		private java.lang.Integer status;
		private java.lang.Integer jobType;
		private java.lang.String demo;
		private java.util.Date createTime;
		private java.util.Date updateTime;
		private Date runTime;
		private String jobCrontab;
		private Integer jobIndex;
		private Long jobId;
	    
		public void setId(String id) {
			this.id = id;
		}
		public String getId() {
			return id;
		}
		public Date getRunTime() {
		    return runTime;
		}
		
		public void setRunTime(Date runTime) {
		    this.runTime = runTime;
		}
		public java.lang.String getJobClass(){
				return this.jobClass;
		}	
		public void setJobClass(java.lang.String jobClass ){
				this.jobClass = jobClass;
		}
		public java.lang.String getJobParams(){
				return this.jobParams;
		}	
		public void setJobParams(java.lang.String jobParams ){
				this.jobParams = jobParams;
		}
		public java.lang.Integer getStatus(){
				return this.status;
		}	
		public void setStatus(java.lang.Integer status ){
				this.status = status;
		}
		public java.lang.Integer getJobType(){
				return this.jobType;
		}	
		public void setJobType(java.lang.Integer jobType ){
				this.jobType = jobType;
		}
		public java.lang.String getDemo(){
				return this.demo;
		}	
		public void setDemo(java.lang.String demo ){
				this.demo = demo;
		}
		public java.util.Date getCreateTime(){
				return this.createTime;
		}	
		public void setCreateTime(java.util.Date createTime ){
				this.createTime = createTime;
		}
		public java.util.Date getUpdateTime(){
				return this.updateTime;
		}	
		public void setUpdateTime(java.util.Date updateTime ){
				this.updateTime = updateTime;
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

		public Long getJobId() {
			return jobId;
		}

		public void setJobId(Long jobId) {
			this.jobId = jobId;
		}
		
		
}