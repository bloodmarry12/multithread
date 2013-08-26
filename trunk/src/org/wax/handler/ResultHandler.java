package org.wax.handler;

import org.wax.task.BaseTask;

public interface ResultHandler <V>{
	public void handleResult(BaseTask<V> task,V result)throws Exception;
}
