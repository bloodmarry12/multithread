package org.wax.task;

public class TaskExecuteInfo<V> {
	public enum TaskState{
		ready,starting,canceled,exception,complete
	}
	/**
	 * 任务执行状态
	 */
	private TaskState taskState=TaskState.ready;
	/**
	 * 任务执行中的异常信息
	 */
	private Exception exception;
	/**
	 * 任务执行开始时间
	 */
	private long startTime;
	
	/**
	 * 任务执行结束时间
	 */
	private long endTime;
	/**
	 * 执行结果
	 */
	private V result;
	/**
	 * 执行进度
	 */
	private Integer process=0;
	
	
	public TaskState getTaskState() {
		return taskState;
	}
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public V getResult() {
		return result;
	}
	public void setResult(V result) {
		this.result = result;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
}
