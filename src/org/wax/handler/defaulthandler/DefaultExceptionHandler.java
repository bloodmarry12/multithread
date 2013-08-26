package org.wax.handler.defaulthandler;

import org.wax.handler.ExceptionHandler;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class DefaultExceptionHandler<V> implements ExceptionHandler<V>{

	private DefaultTaskGroup<V> taskGroup;  
	public DefaultExceptionHandler(DefaultTaskGroup<V> taskGroupExecutor){
		this.taskGroup = taskGroupExecutor;
	}
	@Override
	public void handleException(BaseTask<V> task, Exception e) {
		taskGroup.handleException(task,e);
		
	}
	

}
