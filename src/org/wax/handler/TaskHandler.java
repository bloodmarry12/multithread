package org.wax.handler;

import org.wax.task.BaseTask;

public interface TaskHandler<V> {

	void handleTaskBefore(BaseTask<V> task)throws Exception;
	void handleTaskAfter(BaseTask<V> task)throws Exception;
	
}
