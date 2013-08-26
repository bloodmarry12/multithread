package org.wax.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

import org.wax.exception.GroupNameAlreadyExistException;
import org.wax.futuretask.ExecutorFutureTask;
import org.wax.task.BaseTask;
import org.wax.task.TaskExecuteInfo.TaskState;
import org.wax.task.TaskGroup;

@SuppressWarnings("rawtypes")
public class TaskExecutor {

	private TaskExecuteService service;

	private Map<String,TaskGroup> groupMap=new HashMap<String,TaskGroup>();
	public TaskExecutor() {
		service = new TaskExecuteService();
		this.start();
	}

	public TaskExecutor(Executor executor) {
		service = new TaskExecuteService(executor);
		this.start();
	}

	public void shutDown(){
		service.shutDown();
	}
	
	public void shutDownNow(){
		service.shutDownNow(groupMap);
	}
	
	@SuppressWarnings("unchecked")
	public <V> void submitTaskGroup(TaskGroup<V> taskGroup)throws GroupNameAlreadyExistException {
		if (taskGroup.getTasks() == null)
			return;
		if(groupMap.get(taskGroup.getGroupName())!=null)
			throw new GroupNameAlreadyExistException();
		
		groupMap.put(taskGroup.getGroupName(),taskGroup);
		for (BaseTask<V> task : taskGroup.getTasks()) {
			try{
			service.submit(task);
			}catch(RejectedExecutionException e){
				taskGroup.handleReject(task);
				groupMap.remove(taskGroup.getGroupName());
			}
		}
	}

	private void start() {
		Thread t = new TaskListener();
		t.start();
	}

	class TaskListener extends Thread {

		@SuppressWarnings({ "unchecked" })
		public void run() {
			while (true) {
				BaseTask task = null;
				Object result = null;
				try {
					ExecutorFutureTask eft = service.take();
					task = eft.getTask();
					result = eft.get();
					
				}catch(CancellationException e){
					task.getTaskExecuteInfo().setTaskState(TaskState.canceled);
				} catch (Exception e) {
					task.getTaskExecuteInfo().setTaskState(TaskState.exception);
					task.getTaskExecuteInfo().setException(e);
					task.getTaskGroup().getExceptionHandler()
							.handleException(task, e);
				}
				try {
					task.getTaskGroup().getResultHandler()
							.handleResult(task, result);
					task.getTaskExecuteInfo().setTaskState(TaskState.complete);
				} catch (Exception e) {
					task.getTaskExecuteInfo().setException(e);
					task.getTaskGroup().getExceptionHandler()
							.handleException(task, e);
				}
			}
		}
	}

}
