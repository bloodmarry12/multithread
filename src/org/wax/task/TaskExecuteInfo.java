package org.wax.task;

public class TaskExecuteInfo<V> {
	public enum TaskState{
		ready,starting,canceled,exception,complete
	}
	/**
	 * ����ִ��״̬
	 */
	private TaskState taskState=TaskState.ready;
	/**
	 * ����ִ���е��쳣��Ϣ
	 */
	private Exception exception;
	/**
	 * ����ִ�п�ʼʱ��
	 */
	private long startTime;
	
	/**
	 * ����ִ�н���ʱ��
	 */
	private long endTime;
	/**
	 * ִ�н��
	 */
	private V result;
	/**
	 * ִ�н���
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
