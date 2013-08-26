package org.wax.task;

import java.util.List;

import org.wax.exception.TaskNameAlreadyExistExeption;
import org.wax.handler.ExceptionHandler;
import org.wax.handler.ResultHandler;
import org.wax.handler.TaskHandler;
import org.wax.task.TaskExecuteInfo.TaskState;

public interface TaskGroup<V> {
	 Long getTastExecuteTimeByTaskName(String taskName);
	void handleReject(BaseTask<V> task);
	String getGroupName();
	void addTask(BaseTask<V> task)throws TaskNameAlreadyExistExeption;
	
	 ExceptionHandler<V> getExceptionHandler() ;
	 void setExceptionHandler(ExceptionHandler<V> exceptionHandler);

	 TaskHandler<V> getTaskHandler() ;
	 void setTaskHandler(TaskHandler<V> taskHandler) ;

	 ResultHandler<V> getResultHandler() ;
	 void setResultHandler(ResultHandler<V> resultHandler);
	 
	public List<BaseTask<V>> getTasks() ;
	
	
	public BaseTask<V> getTaskByName(String taskName);
	
	public Integer getProcessByTaskName(String taskName);
	public TaskState getTaskStateByTaskName(String taskName);
	
	public void cancelTask(String taskName)throws Exception;
}
