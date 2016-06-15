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
import com.dangdang.ddframe.job.plugin.job.type.simple.AbstractSimpleElasticJob;

@Component
public class MyElasticJob extends AbstractSimpleElasticJob {
	Logger log = Logger.getLogger(getClass());
	static List<Long> dbMock = new ArrayList<Long>();
	static {
		dbMock.add(9L);
		dbMock.add(7L);
		dbMock.add(5L);
		dbMock.add(4L);
		dbMock.add(6L);
	}
    @Override
    public void process(final JobExecutionMultipleShardingContext context) {
    		log.info("自定义简单任务-"+context.getShardingItems()+"-"+context.getShardingTotalCount());
    		for(Long value:dbMock){
    			for(int item:context.getShardingItems()){
    				if(value%context.getShardingTotalCount()==item){
        				log.info("处理ID为："+value+",分片值为："+item);
        			}
    			}
    			
    		}
    }
}
