package org.wax.handler.defaulthandler;

import org.wax.handler.ResultHandler;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class DefaultResultHandler<V> implements ResultHandler<V> {
	private DefaultTaskGroup<V> taskGroup;  
	public DefaultResultHandler(DefaultTaskGroup<V> taskGroupExecutor){
		this.taskGroup = taskGroupExecutor;
	}
	public void handleResult(BaseTask<V> task,V result)throws Exception{
		taskGroup.handleResult(task,result);
	}
	
}
