package org.wax.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.wax.exception.TaskNameAlreadyExistExeption;
import org.wax.handler.ExceptionHandler;
import org.wax.handler.ResultHandler;
import org.wax.handler.TaskHandler;
import org.wax.handler.defaulthandler.DefaultExceptionHandler;
import org.wax.handler.defaulthandler.DefaultResultHandler;
import org.wax.handler.defaulthandler.DefaultTaskHandler;
import org.wax.task.TaskExecuteInfo.TaskState;

import com.sun.org.apache.bcel.internal.generic.IRETURN;

public class DefaultTaskGroup<V> implements TaskGroup<V> {
	private Logger logger=Logger.getLogger(getClass());
	
	private Map<String,BaseTask<V>> taskMap=new HashMap<String,BaseTask<V>>();
	private ExceptionHandler<V> exceptionHandler=new DefaultExceptionHandler<V>(this);
	private TaskHandler<V> taskHandler=new DefaultTaskHandler<V>(this);
	private ResultHandler<V> resultHandler=new DefaultResultHandler<V>(this);
	
	private final String groupName;

	public DefaultTaskGroup(String groupName){
		this.groupName=groupName;
	}
	public BaseTask<V> getTaskByName(String taskName){
		return taskMap.get(taskName);
	}
	public Long getTastExecuteTimeByTaskName(String taskName){
		TaskExecuteInfo i=taskMap.get(taskName).getTaskExecuteInfo();
		return (i.getEndTime()-i.getStartTime())/1000;
	}
	public Integer getProcessByTaskName(String taskName){
		BaseTask<V> task=this.getTaskByName(taskName);
		return task.getTaskExecuteInfo().getProcess();
	}
	public TaskState getTaskStateByTaskName(String taskName){
		BaseTask<V> task=this.getTaskByName(taskName);
		return task.getTaskExecuteInfo().getTaskState();
	}
	
	public void cancelTask(String taskName)throws Exception{
		BaseTask<V> task=this.getTaskByName(taskName);
		task.cancelTask();
		task.getTaskExecuteInfo().setTaskState(TaskState.canceled);
	}
	public void addTask(BaseTask<V> task)throws TaskNameAlreadyExistExeption{
		if(this.getTaskByName(task.getTaskName())!=null)
			throw new TaskNameAlreadyExistExeption();
		
		task.setTaskGroup(this);
		taskMap.put(task.getTaskName(),task);
	}
	public TaskHandler<V> getTaskHandler() {
		return taskHandler;
	}

	public void setTaskHandler(TaskHandler<V> taskHandler) {
		this.taskHandler = taskHandler;
	}
	
	public ExceptionHandler<V> getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(ExceptionHandler<V> exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public ResultHandler<V> getResultHandler() {
		return resultHandler;
	}

	public void setResultHandler(ResultHandler<V> resultHandler) {
		this.resultHandler = resultHandler;
	}
	public List<BaseTask<V>> getTasks() {
		List<BaseTask<V>> res=new ArrayList<BaseTask<V>>();
		if(taskMap==null)
			return res;
		for(String taskName:taskMap.keySet()){
			res.add(taskMap.get(taskName));
		}
		return res;
	}
	
	
	
	public void handleException(BaseTask<V> task,Exception e){
		logger.debug(e);
	}

	public void handleTaskBefore(BaseTask<V> task)throws Exception{
		logger.debug(task.getTaskName()+" starting..");
		task.getTaskExecuteInfo().setStartTime(new Date().getTime());
	}
	
	public void handleTaskAfter(BaseTask<V> task)throws Exception{
		task.getTaskExecuteInfo().setEndTime(new Date().getTime());
		logger.debug(task.getTaskName()+" end");
	}
	public  void handleResult(BaseTask<V> task,V result)throws Exception{
		logger.debug(task.getTaskName()+" result:"+result);
	}

	public String getGroupName() {
		return groupName;
	}


	@Override
	public void handleReject(BaseTask<V> task) {
		logger.debug(task.getTaskName()+" rejected!");
	}


}
