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
		logger.debug(task.getTaskName()+"�����쳣��!"+e);
	}

	public void handleTaskBefore(BaseTask<V> task)throws Exception{
		super.handleTaskBefore(task);
		logger.debug(task.getTaskName()+"��ʼִ��...");
	}
	public void handleTaskAfter(BaseTask<V> task)throws Exception{
		super.handleTaskAfter(task);
		Long time=task.getTaskGroup().getTastExecuteTimeByTaskName(task.getTaskName());
		
		logger.debug(task.getTaskName()+"ִ�н���! ��ִ��"+time+"�룡");
	}
	public  void handleResult(BaseTask<V> task,V result)throws Exception{
		super.handleResult(task, result);
		logger.debug("handleResult:"+result);
	}
	
	public void handleReject(BaseTask<V> task) {
		logger.debug(task.getTaskName()+" ���ܾ�ִ�У��̳߳��Ѿ�ֹͣ!");
	}
}
