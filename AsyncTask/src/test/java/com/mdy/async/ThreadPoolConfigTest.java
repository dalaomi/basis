package com.mdy.async;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mdy.async.EaseConsumer;
import com.mdy.async.job.AbstractJobExecutor;
import com.mdy.async.model.AsyncJob;
import com.mdy.async.thread.ThreadPoolConfig;
import com.mdy.async.thread.ThreadPoolInfo;

/**
 * {@link ThreadPoolConfig}的单元测试用例。
 * 
 */
public class ThreadPoolConfigTest {

    private ThreadPoolConfig _threadPoolConfig = new ThreadPoolConfig();
    
    @Before
    public void setUp() throws Exception {
    	EaseConsumer.getInstance().init();
    }
    
    @After
    public void tearDown() throws Exception {
    	
    }
    
    /**
     * 测试用例：读取线程池配置文件 <br/>
     * 前置条件：
     * <pre>
     * 1.0.0版本的配置文件，没有线程状态输出开关（threadstate节点）
     * </pre>
     * 
     * 测试结果：
     * <pre>
     * 1、有两个线程池的配置信息，分别是default和other。
     * 2、线程状态输出开关的值为false。
     * </pre>
     */
    @Test
    public void test1() throws Exception {
    	//模拟从库获取任务列表
    	List<AsyncJob> asyncJobs = new ArrayList<AsyncJob>();
    	for(int i = 0;i<1;i++){
    		AsyncJob asyncJob = new AsyncJob();
    		asyncJob.setId(i+"");
    		asyncJob.setJobClass("com.mdy.async.sample.DemoJobExecutor");
    		asyncJob.setJobParams("{param:param1}1");
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
     * 测试用例：读取线程池配置文件 <br/>
     * 前置条件：
     * <pre>
     * 1、1.5.0版本的配置文件
     * 2、有线程池状态输出开关（threadpoolstate节点），配置为on
     * 3、有线程状态输出开关（threadstate节点），配置为on
     * </pre>
     * 
     * 测试结果：
     * <pre>
     * 1、有两个线程池的配置信息，分别是default和other。
     * 2、线程池状态输出开关的值为true，输出间隔为120秒。
     * 3、线程状态输出开关的值为true，输出间隔为180秒。
     * </pre>
     */
    @Test
    public void testInit41_5_0() {
        _threadPoolConfig._configFile = "/cn/aofeng/threadpool4j/threadpool4j_1.5.0.xml";
        _threadPoolConfig.init();
        assertEquals(2, _threadPoolConfig._multiThreadPoolInfo.size());
        
        // default线程池配置信息
        assertTrue(_threadPoolConfig._multiThreadPoolInfo.containsKey("default"));
        ThreadPoolInfo defaultInfo = _threadPoolConfig._multiThreadPoolInfo.get("default");
        assertEquals(30, defaultInfo.getCoreSize());
        assertEquals(150, defaultInfo.getMaxSize());
        assertEquals(5, defaultInfo.getThreadKeepAliveTime());
        assertEquals(100000, defaultInfo.getQueueSize());
        
        // other线程池配置信息
        assertTrue(_threadPoolConfig._multiThreadPoolInfo.containsKey("other"));
        ThreadPoolInfo otherInfo = _threadPoolConfig._multiThreadPoolInfo.get("other");
        assertEquals(10, otherInfo.getCoreSize());
        assertEquals(100, otherInfo.getMaxSize());
        assertEquals(10, otherInfo.getThreadKeepAliveTime());
        assertEquals(10000, otherInfo.getQueueSize());
        
        // 线程池状态统计配置信息
        assertTrue(_threadPoolConfig._threadPoolStateSwitch);
        assertEquals(120, _threadPoolConfig._threadPoolStateInterval);
        
        // 线程状态统计配置信息
        assertTrue(_threadPoolConfig._threadStateSwitch);
        assertEquals(180, _threadPoolConfig._threadStateInterval);
    }
    
    /**
     * 测试用例：读取线程池配置文件 <br/>
     * 前置条件：
     * <pre>
     * 1、1.5.0版本的配置文件
     * 2、有线程池状态输出开关（threadpoolstate节点），配置为on
     * 3、有线程状态输出开关（threadstate节点），配置为off
     * </pre>
     * 
     * 测试结果：
     * <pre>
     * 1、有两个线程池的配置信息，分别是default和hello。
     * 2、线程池状态输出开关的值为true，输出间隔为120秒。
     * 3、线程状态输出开关的值为false，输出间隔为100秒。
     * </pre>
     */
    @Test
    public void testInit41_5_0CloseThreadStateSwitch() {
        _threadPoolConfig._configFile = "/cn/aofeng/threadpool4j/threadpool4j_1.5.0_closethreadstate.xml";
        _threadPoolConfig.init();
        assertEquals(2, _threadPoolConfig._multiThreadPoolInfo.size());
        
        // default线程池配置信息
        assertTrue(_threadPoolConfig._multiThreadPoolInfo.containsKey("default"));
        ThreadPoolInfo defaultInfo = _threadPoolConfig._multiThreadPoolInfo.get("default");
        assertEquals(30, defaultInfo.getCoreSize());
        assertEquals(150, defaultInfo.getMaxSize());
        assertEquals(5, defaultInfo.getThreadKeepAliveTime());
        assertEquals(100000, defaultInfo.getQueueSize());
        
        // hello线程池配置信息
        assertTrue(_threadPoolConfig._multiThreadPoolInfo.containsKey("hello"));
        ThreadPoolInfo otherInfo = _threadPoolConfig._multiThreadPoolInfo.get("hello");
        assertEquals(10, otherInfo.getCoreSize());
        assertEquals(100, otherInfo.getMaxSize());
        assertEquals(10, otherInfo.getThreadKeepAliveTime());
        assertEquals(10000, otherInfo.getQueueSize());
        
        // 线程池状态统计配置信息
        assertFalse(_threadPoolConfig._threadPoolStateSwitch);
        assertEquals(120, _threadPoolConfig._threadPoolStateInterval);
        
        // 线程状态统计配置信息
        assertFalse(_threadPoolConfig._threadStateSwitch);
        assertEquals(100, _threadPoolConfig._threadStateInterval);
    }

}
