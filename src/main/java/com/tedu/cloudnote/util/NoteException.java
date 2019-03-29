package com.tedu.cloudnote.util;

/**
 * 自定义异常
 * 
 * @author 10174
 *
 */
public class NoteException extends RuntimeException {
	public NoteException(String msg, Throwable t) {
		super(msg, t);
	}
}
