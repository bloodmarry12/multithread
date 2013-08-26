package org.wax.task;

import java.util.concurrent.Callable;

import org.wax.futuretask.ExecutorFutureTask;
import org.wax.task.TaskExecuteInfo.TaskState;


public abstract class BaseTask<V> implements Callable<V>{
	
	
	private TaskGroup<V> taskGroup;
	
	private final String taskName;
	
	
	private TaskExecuteInfo<V> taskExecuteInfo=new TaskExecuteInfo<V>();
	
	
	public BaseTask(String taskName){
		this.taskName=taskName;
	}
	
	
	public void cancelTask()throws Exception{
		
	}
	public V call()throws Exception{
		this.taskGroup.getTaskHandler().handleTaskBefore(this);
		this.taskExecuteInfo.setTaskState(TaskState.starting);
		V result= this. execute();
		this.taskExecuteInfo.setTaskState(TaskState.complete);
		this.taskGroup.getTaskHandler().handleTaskAfter(this);
		return result;
	}
	/**
	 * 执行逻辑，结果自己保存
	 * 
	 */
	public abstract V execute() throws Exception;
	

	public String getTaskName() {
		return taskName;
	}

	public TaskGroup<V> getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(TaskGroup<V> taskGroup) {
		this.taskGroup = taskGroup;
	}


	public TaskExecuteInfo<V> getTaskExecuteInfo() {
		return taskExecuteInfo;
	}


	public void setTaskExecuteInfo(TaskExecuteInfo<V> taskExecuteInfo) {
		this.taskExecuteInfo = taskExecuteInfo;
	}

	
	
}
