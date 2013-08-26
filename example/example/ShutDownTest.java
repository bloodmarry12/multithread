package example;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.wax.executor.TaskExecutor;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class ShutDownTest  extends BaseTask<List<String>>{
private final Logger logger = Logger.getLogger(getClass());
	
	public ShutDownTest(String taskName){
		super(taskName);
	}
	@Override
	public List<String> execute() throws Exception{
		
		List<String> result=new ArrayList<String>();
		
		for(int i=0;i<10;i++){
			logger.debug("test "+i);
			Thread.sleep(1000);
			result.add(""+i);
		}
		return result;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)throws Exception {
		
		DefaultTaskGroup group=new DefaultTaskGroup("myGroupName");
		group.addTask(new ShutDownTest("myTaskName"));
		TaskExecutor te=new TaskExecutor();
		te.submitTaskGroup(group);
		
		Thread.sleep(3000);
		//执行器停止
		te.shutDown();
		
		//任务停止后再次往线程池中加入新的任务则会被拒绝，
		//会调用TaskGroup的handleReject方法进行处理
		DefaultTaskGroup group2=new DefaultTaskGroup("myGroupName2");
		group2.addTask(new ShutDownTest("myTaskName2"));
		te.submitTaskGroup(group2);
		
	}
}
