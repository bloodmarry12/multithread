package org.wax.futuretask;

import java.util.concurrent.FutureTask;

import org.wax.task.BaseTask;

public class ExecutorFutureTask<V> extends FutureTask<V> {
	private final BaseTask<V> task;

	public ExecutorFutureTask(BaseTask<V> task) {
		super(task);
		this.task = task;
	}

	public BaseTask<V> getTask() {
		return task;
	}
	
}
