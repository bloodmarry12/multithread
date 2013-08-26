package org.wax.exception;

public class TaskNameAlreadyExistExeption extends Exception {
	private static final long serialVersionUID = 1L;

	public TaskNameAlreadyExistExeption(){
		super("task name is already exist");
	}
}
