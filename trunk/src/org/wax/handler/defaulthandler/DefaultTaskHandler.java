package org.wax.handler.defaulthandler;

import org.wax.handler.TaskHandler;
import org.wax.task.BaseTask;
import org.wax.task.DefaultTaskGroup;

public class DefaultTaskHandler<V> implements TaskHandler<V>{

	private DefaultTaskGroup<V> taskGroup;  
	public DefaultTaskHandler(DefaultTaskGroup<V> taskGroupExecutor){
		this.taskGroup = taskGroupExecutor;
	}
	@Override
	public void handleTaskBefore(BaseTask<V> task) throws Exception {
		taskGroup.handleTaskBefore(task);
	}
	@Override
	public void handleTaskAfter(BaseTask<V> task) throws Exception {
		taskGroup.handleTaskAfter(task);
	}
}
