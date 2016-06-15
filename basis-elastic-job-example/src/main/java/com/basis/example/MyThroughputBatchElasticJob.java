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
import com.dangdang.ddframe.job.plugin.job.type.dataflow.AbstractBatchThroughputDataFlowElasticJob;

//@Component
public class MyThroughputBatchElasticJob extends AbstractBatchThroughputDataFlowElasticJob<Integer> {

	@Override
	public int processData(JobExecutionMultipleShardingContext shardingContext, List<Integer> data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Integer> fetchData(JobExecutionMultipleShardingContext shardingContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStreamingProcess() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
