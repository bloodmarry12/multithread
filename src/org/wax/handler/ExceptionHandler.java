package org.wax.handler;

import org.wax.task.BaseTask;

public interface ExceptionHandler<V> {
	void handleException(BaseTask<V> task,Exception e);
}
