package example;

import org.apache.log4j.Logger;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;


public class CustomTaskGroup<V>  extends DefaultTaskGroup<V>{
	private Logger logger=Logger.getLogger(getClass());
	public CustomTaskGroup(String groupName){
		super(groupName);
	}
	public void handleException(BaseTask<V> task,Exception e){
		super.handleException(task, e);
		logger.debug(task.getTaskName()+"出现异常了!"+e);
	}

	public void handleTaskBefore(BaseTask<V> task)throws Exception{
		super.handleTaskBefore(task);
		logger.debug(task.getTaskName()+"开始执行...");
	}
	public void handleTaskAfter(BaseTask<V> task)throws Exception{
		super.handleTaskAfter(task);
		Long time=task.getTaskGroup().getTastExecuteTimeByTaskName(task.getTaskName());
		
		logger.debug(task.getTaskName()+"执行结束! 共执行"+time+"秒！");
	}
	public  void handleResult(BaseTask<V> task,V result)throws Exception{
		super.handleResult(task, result);
		logger.debug("handleResult:"+result);
	}
	
	public void handleReject(BaseTask<V> task) {
		logger.debug(task.getTaskName()+" 被拒绝执行，线程池已经停止!");
	}
}
