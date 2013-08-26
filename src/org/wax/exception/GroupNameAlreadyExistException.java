package org.wax.exception;

public class GroupNameAlreadyExistException  extends Exception{
	private static final long serialVersionUID = 1L;

	public GroupNameAlreadyExistException(){
		super("group name is already exist");
	}
}
