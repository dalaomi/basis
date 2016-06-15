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
import com.dangdang.ddframe.job.api.JobExecutionSingleShardingContext;
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractIndividualSequenceDataFlowElasticJob;

//@Component
public class MySequenceDataFlowElasticJob extends AbstractIndividualSequenceDataFlowElasticJob<Integer> {
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
	public List<Integer> fetchData(JobExecutionSingleShardingContext context) {
		List<Integer> result = new ArrayList<Integer>();
		log.info("Throughput任务-fetchData-" + context.getShardingItem() + "-" + context.getShardingTotalCount());
		for (Integer value : dbMock) {
			int item = context.getShardingItem();
			if (value % context.getShardingTotalCount() == item) {
				result.add(value);
			}
		}
		log.info("Throughput任务-fetchData-" + context.getShardingItem() + "-" + context.getShardingTotalCount()+",result="+result);
		return result;
	}

	@Override
	public boolean isStreamingProcess() {
		return false;
	}

	@Override
	public boolean processData(JobExecutionSingleShardingContext context, Integer value) {
		// TODO Auto-generated method stub
		log.info("Throughput任务-processData-" + context.getShardingItem() + "-" + context.getShardingTotalCount()+",value="+value);
		return false;
	}




}
