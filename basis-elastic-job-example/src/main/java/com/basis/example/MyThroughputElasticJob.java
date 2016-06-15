/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.basis.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.JobExecutionMultipleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualThroughputDataFlowElasticJob;

//@Component
public class MyThroughputElasticJob extends AbstractIndividualThroughputDataFlowElasticJob<Integer> {
	Logger log = Logger.getLogger(getClass());
	static List<Integer> dbMock = new ArrayList<Integer>();

	static {
		dbMock.add(9);
		dbMock.add(7);
		dbMock.add(5);
		dbMock.add(4);
		dbMock.add(6);
	}

	@Override
	public List<Integer> fetchData(JobExecutionMultipleShardingContext context) {
		context.getFetchDataCount();
		List<Integer> result = new ArrayList<Integer>();
		//log.info("Throughput任务-fetchData-" + context.getShardingItems() + "-" + context.getShardingTotalCount());
		for (Integer value : dbMock) {
			for (int item : context.getShardingItems()) {
				if (value % context.getShardingTotalCount() == item) {
					result.add(value);
				}
			}
		}
		log.info("Throughput任务-fetchData-" + context.getShardingItems() + "-" + context.getShardingTotalCount()+",result="+result);
		return result;
	}

	@Override
	public boolean isStreamingProcess() {
		return false;
	}
	/**
	 * 设定处理线程数为3，如果处理数据条数小于3，则只有一个线程，否则3个
	 * 1、假定处理线程数为3:如果一次取出100条数据，则每个线程处理数据条数｛34，33，33｝
	 */
	@Override
	public boolean processData(JobExecutionMultipleShardingContext context, Integer value) {
		// TODO Auto-generated method stub
		log.info("Throughput任务-processData-" + context.getShardingItems() + "-" + context.getShardingTotalCount()+",value="+value);
		return false;
	}

}
